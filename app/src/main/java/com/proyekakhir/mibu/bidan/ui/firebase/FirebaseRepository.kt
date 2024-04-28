package com.proyekakhir.mibu.bidan.ui.firebase

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.databinding.adapters.NumberPickerBindingAdapter.setValue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
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
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.ChildItem
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
        val fileName = UUID.randomUUID().toString() + ".jpg"
        val refStorage = FirebaseStorage.getInstance().reference.child("posterArtikel/$fileName")

        if (selectedImageUri != null) {
            refStorage.putFile(selectedImageUri)
                .addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.storage.downloadUrl.addOnSuccessListener { imageUrl ->
                        saveArtikelToDatabase(judul, isiArtikel, imageUrl.toString(), onSuccess, onFailure)
                    }
                }
                .addOnFailureListener { e ->
                    onFailure(e)
                }
        } else {
            // Handle the case when selectedImageUri is null (e.g., upload article without an image)
            saveArtikelToDatabase(judul, isiArtikel, "", onSuccess, onFailure)
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
                dataSnapshot.children.forEach {
                    val key = it.key
                    val artikel = it.getValue(ArtikelData::class.java)
                    if (artikel != null && artikel.uid == uid) {
                        artikel.key = key
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

    override fun getCatatanKesehatan(
        uid: String,
        onDataChanged: (ArrayList<AddKesehatanKehamilanData>) -> Unit,
        onIsEmpty: (String) -> Unit,
        onIsLoading: (Boolean) -> Unit,
        onCancelled: (DatabaseError) -> Unit
    ) {
        onIsLoading(true)
        val database = FirebaseDatabase.getInstance().getReference("CatatanKesehatanKehamilan")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = arrayListOf<AddKesehatanKehamilanData>()
                snapshot.children.forEach { firstChild ->
                    val firstChildKey = firstChild.key
                    firstChild.children.forEach { secondChild ->
                        val data = secondChild.getValue(AddKesehatanKehamilanData::class.java)
                        val key = secondChild.key
                        if (data != null) {
                            data.key = key // Set the key
                            data.firstChildKey = firstChildKey
                            if (data.uid == uid){
                                list.add(data)
                            }
                        }
                    }
                }
                onDataChanged(list)
                onIsEmpty(if (list.isEmpty()) "No Data Found" else "")
                onIsLoading(false)
            }

            override fun onCancelled(error: DatabaseError) {
                onCancelled(error)
            }
        })
    }

    override fun getCatatanNifas(
        uid: String,
        onDataChanged: (ArrayList<AddNifasData>) -> Unit,
        onIsEmpty: (String) -> Unit,
        onIsLoading: (Boolean) -> Unit,
        onCancelled: (DatabaseError) -> Unit
    ) {
        onIsLoading(true)
        val database = FirebaseDatabase.getInstance().getReference("CatatanNifas")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = arrayListOf<AddNifasData>()
                snapshot.children.forEach { firstChild ->
                    val firstChildKey = firstChild.key
                    firstChild.children.forEach { secondChild ->
                        val data = secondChild.getValue(AddNifasData::class.java)
                        val key = secondChild.key
                        if (data != null) {
                            data.key = key // Set the key
                            data.firstChildKey = firstChildKey
                            if (data.uid == uid){
                                list.add(data)
                            }
                        }
                    }
                }
                onDataChanged(list)
                onIsEmpty(if (list.isEmpty()) "No Data Found" else "")
                onIsLoading(false)
            }

            override fun onCancelled(error: DatabaseError) {
                onCancelled(error)
            }
        })
    }

    override fun getCatatanAnak(
        uid: String,
        onDataChanged: (ArrayList<AddDataAnak>) -> Unit,
        onIsEmpty: (String) -> Unit,
        onIsLoading: (Boolean) -> Unit,
        onCancelled: (DatabaseError) -> Unit
    ) {
        onIsLoading(true)
        val database = FirebaseDatabase.getInstance().getReference("CatatanAnak")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = arrayListOf<AddDataAnak>()
                snapshot.children.forEach { firstChild ->
                    val firstChildKey = firstChild.key
                    firstChild.children.forEach { secondChild ->
                        val data = secondChild.getValue(AddDataAnak::class.java)
                        val key = secondChild.key
                        if (data != null) {
                            data.key = key // Set the key
                            data.firstChildKey = firstChildKey
                            if (data.uid == uid){
                                list.add(data)
                            }
                        }
                    }
                }
                onDataChanged(list)
                onIsEmpty(if (list.isEmpty()) "No Data Found" else "")
                onIsLoading(false)
            }

            override fun onCancelled(error: DatabaseError) {
                onCancelled(error)
            }
        })
    }

    override fun deleteArtikel(
        artikelId: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        // Construct the reference to the specific article node
        val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
        val artikelRef = databaseReference.child("artikel").child(artikelId)

        // Remove the article from the database
        artikelRef.removeValue()
            .addOnSuccessListener {
                // Article deleted successfully
                onSuccess()
            }
            .addOnFailureListener { e ->
                // Handle the error
                onFailure(e)
            }
    }

    override fun updateArtikel(
        itemKey: String,
        updatedArtikel: ArtikelData,
        onComplete: (Boolean) -> Unit
    ) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("artikel")

        // Update the article data
        databaseReference.child(itemKey).setValue(updatedArtikel)
            .addOnSuccessListener {
                // Handle success (if needed)
                onComplete(true)
            }
            .addOnFailureListener { e ->
                // Handle failure (if needed)
                onComplete(false)
            }
    }

    override fun updateKesehatan(
        uid: String,
        itemKey: String,
        updatedKesehatan: AddKesehatanKehamilanData,
        onComplete: (Boolean) -> Unit
    ) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("CatatanKesehatanKehamilan")

        // Construct the path to the specific child node based on uid and itemKey
        val childPath = "$uid/$itemKey"

        databaseReference.child(childPath).setValue(updatedKesehatan)
            .addOnSuccessListener {
                // Handle success (if needed)
                onComplete(true)
            }
            .addOnFailureListener { e ->
                // Handle failure (if needed)
                onComplete(false)
            }
    }

    override fun deleteKesehatan(
        uid: String,
        itemKey: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        // Construct the reference to the specific article node
        val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("CatatanKesehatanKehamilan")
        val childPath = "$uid/$itemKey"

        // Remove the article from the database
        databaseReference.child(childPath).removeValue()
            .addOnSuccessListener {
                // Article deleted successfully
                onSuccess()
            }
            .addOnFailureListener { e ->
                // Handle the error
                onFailure(e)
            }
    }

    override fun updateNifas(
        uid: String,
        itemKey: String,
        updatedNifas: AddNifasData,
        onComplete: (Boolean) -> Unit
    ) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("CatatanNifas")

        // Construct the path to the specific child node based on uid and itemKey
        val childPath = "$uid/$itemKey"

        databaseReference.child(childPath).setValue(updatedNifas)
            .addOnSuccessListener {
                // Handle success (if needed)
                onComplete(true)
            }
            .addOnFailureListener { e ->
                // Handle failure (if needed)
                onComplete(false)
            }
    }

    override fun updateAnak(
        uid: String,
        itemKey: String,
        updatedAnak: AddDataAnak,
        onComplete: (Boolean) -> Unit
    ) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("CatatanAnak")

        // Construct the path to the specific child node based on uid and itemKey
        val childPath = "$uid/$itemKey"

        databaseReference.child(childPath).setValue(updatedAnak)
            .addOnSuccessListener {
                // Handle success (if needed)
                onComplete(true)
            }
            .addOnFailureListener { e ->
                // Handle failure (if needed)
                onComplete(false)
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