package com.proyekakhir.mibu.bidan.ui.firebase

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import com.proyekakhir.mibu.bidan.ui.auth.preferences.PreferenceManager
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel.ArtikelData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddDataAnak
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddNifasData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.IbuHamilData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.settings.UserData
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class FirebaseRepository : FirebaseService {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    override fun login(email: String, password: String, onComplete: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                val userId = auth?.uid
                onComplete(task.isSuccessful)
            }
    }

    override fun signupBidan(
        fullname: String,
        alamat: String,
        email: String,
        noTelepon: String,
        str: String,
        password: String,
        onComplete: (Boolean) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    // Update user profile with display name
                    val user = auth.currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(fullname)
                        .build()

                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { profileTask ->
                            if (profileTask.isSuccessful){
                                val bidanData = hashMapOf(
                                    "fullname" to fullname,
                                    "alamat" to alamat,
                                    "email" to email,
                                    "noTelepon" to noTelepon,
                                    "str" to str,
                                    "uid" to user?.uid,
                                    "role" to "bidan"
                                )


                                //insert data to firestore
                                val uid = user?.uid
                                if (uid != null){
                                    firestore.collection("users").document(uid)
                                        .set(bidanData)
                                        .addOnSuccessListener {
                                            onComplete(true)
                                        }
                                        .addOnFailureListener {
                                            Log.d("Firestore Error", it.message!!)
                                            onComplete(false)
                                        }
                                } else {
                                    onComplete(false)
                                }

                            } else {
                                onComplete(false)
                            }
                        }
                        ?.addOnFailureListener {
                            onComplete(false)
                        }
                } else {
                    onComplete (false)
                }
            }
    }

    override fun getAllIbuHamil(
        role: String,
        onDataChange: (List<IbuHamilData>) -> Unit,
        onCancelled: (DatabaseError) -> Unit
    ) {
        firestore.collection("users")
            .whereEqualTo("role", role)
            .get()
            .addOnSuccessListener { documents ->
                val list = documents.mapNotNull { document ->
                    val ibu = document.toObject(IbuHamilData::class.java)
                    ibu
                }
                onDataChange(list)
            }
            .addOnFailureListener { e ->
                onCancelled(error(e))
            }
    }

    override fun uploadArtikel(
        judul: String,
        isiArtikel: String,
        selectedImageUri: Uri?,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        if (selectedImageUri != null) {
            val fileName = UUID.randomUUID().toString() + ".jpg"
            val refStorage = FirebaseStorage.getInstance().reference.child("posterArtikel/$fileName")

            refStorage.putFile(selectedImageUri)
                .addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.storage.downloadUrl.addOnSuccessListener { imageUrl ->
                        saveArtikelToDatabase(judul, isiArtikel, imageUrl.toString(), onSuccess, onFailure)
                    }
                }
                .addOnFailureListener { e ->
                    onFailure(e)
                }
        }
    }

    override fun getArtikelByUser(
        onDataChange: (List<ArtikelData>) -> Unit,
        onCancelled: (DatabaseError) -> Unit
    ) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val refDatabase = FirebaseDatabase.getInstance().getReference("artikel")
        val artikelList = mutableListOf<ArtikelData>()

        refDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                artikelList.clear()
                for (postSnapshot in dataSnapshot.children) {
                    val artikel = postSnapshot.getValue(ArtikelData::class.java)
                    if (artikel != null && artikel.uid == uid) {
                        artikelList.add(artikel)
                    }
                }
                onDataChange(artikelList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                onCancelled(databaseError)
            }
        })
    }

    override fun getUserData(onDataChange: (UserData?) -> Unit, onCancelled: (Exception) -> Unit) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val db = FirebaseFirestore.getInstance()

        if (uid != null) {
            db.collection("users").document(uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val userData = document.toObject(UserData::class.java)
                        onDataChange(userData)
                    } else {
                        onDataChange(null)
                    }
                }
                .addOnFailureListener { exception ->
                    onCancelled(exception)
                }
        } else {
            onCancelled(Exception("User not logged in"))
        }
    }

    override fun uploadCatatanKesehatan(
        uid: String,
        formData: AddKesehatanKehamilanData,
        onComplete: (Boolean) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val database = FirebaseDatabase.getInstance().getReference("CatatanKesehatanKehamilan").child(uid).push()

        database.setValue(formData)
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }

    }

    override fun uploadCatatanNifas(
        uid: String,
        formData: AddNifasData,
        onComplete: (Boolean) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val database = FirebaseDatabase.getInstance().getReference("CatatanNifas").child(uid).push()

        database.setValue(formData)
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    override fun uploadDataAnak(
        uid: String,
        formData: AddDataAnak,
        onComplete: (Boolean) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val database = FirebaseDatabase.getInstance().getReference("CatatanAnak").child(uid).push()

        database.setValue(formData)
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    private fun saveArtikelToDatabase(judul: String, isiArtikel: String, imageUrl: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
        val db = FirebaseFirestore.getInstance()

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentDate = sdf.format(Date())

        db.collection("users").document(uid).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val fullName = document.getString("fullname") // replace "fullName" with the actual field name in your Firestore document
                    val artikel = ArtikelData(judul, isiArtikel, imageUrl, uid, currentDate, fullName)
                    val refDatabase = FirebaseDatabase.getInstance().getReference("artikel").push()

                    refDatabase.setValue(artikel)
                        .addOnSuccessListener {
                            onSuccess()
                        }
                        .addOnFailureListener { e ->
                            onFailure(e)
                        }
                } else {
                    Log.d("Firestore", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Firestore", "get failed with ", exception)
            }
    }

}