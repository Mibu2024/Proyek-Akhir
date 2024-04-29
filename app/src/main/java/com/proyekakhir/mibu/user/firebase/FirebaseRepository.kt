package com.proyekakhir.mibu.user.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel.ArtikelData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddDataAnak
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddNifasData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.IbuHamilData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.settings.UserData
import com.proyekakhir.mibu.user.ui.anak.model.AnakModel
import com.proyekakhir.mibu.user.ui.home.model.ArtikelModel
import com.proyekakhir.mibu.user.ui.home.model.BidanData
import com.proyekakhir.mibu.user.ui.home.model.UserModel
import com.proyekakhir.mibu.user.ui.kehamilan.model.KesehatanModel
import com.proyekakhir.mibu.user.ui.kehamilan.model.NifasModel

class FirebaseRepository : FirebaseService {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    override fun login(email: String, password: String, onComplete: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                onComplete(task.isSuccessful)
            }
    }

    override fun signup(
        fullname: String,
        alamat: String,
        email: String,
        noTelepon: String,
        umur: String,
        kehamilanKe: String,
        namaSuami: String,
        umurSuami: String,
        nik: String,
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
                                val userData = hashMapOf(
                                    "fullname" to fullname,
                                    "alamat" to alamat,
                                    "email" to email,
                                    "noTelepon" to noTelepon,
                                    "umur" to umur,
                                    "kehamilanKe" to kehamilanKe,
                                    "namaSuami" to namaSuami,
                                    "umurSuami" to umurSuami,
                                    "nik" to nik,
                                    "uid" to user?.uid,
                                    "role" to "user"
                                )


                                //insert data to firestore
                                val uid = user?.uid
                                if (uid != null){
                                    firestore.collection("users").document(uid)
                                        .set(userData)
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

    override fun getUserData(onDataChange: (UserModel?) -> Unit, onCancelled: (Exception) -> Unit) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val db = FirebaseFirestore.getInstance()

        if (uid != null) {
            db.collection("users").document(uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val userModel = document.toObject(UserModel::class.java)
                        onDataChange(userModel)
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

    override fun getArtikel(
        onDataChange: (List<ArtikelModel>) -> Unit,
        onCancelled: (DatabaseError) -> Unit
    ) {
        val refDatabase = FirebaseDatabase.getInstance().getReference("artikel")
        val artikelList = mutableListOf<ArtikelModel>()

        refDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                artikelList.clear()
                for (postSnapshot in dataSnapshot.children) {
                    val artikel = postSnapshot.getValue(ArtikelModel::class.java)
                    if (artikel != null) {
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

    override fun getCatatanKesehatan(
        uid: String,
        onDataChanged: (ArrayList<KesehatanModel>) -> Unit,
        onIsEmpty: (String) -> Unit,
        onIsLoading: (Boolean) -> Unit,
        onCancelled: (DatabaseError) -> Unit
    ) {
        onIsLoading(true)
        val database = FirebaseDatabase.getInstance().getReference("CatatanKesehatanKehamilan")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = arrayListOf<KesehatanModel>()
                snapshot.children.forEach { firstChild ->
                    val firstChildKey = firstChild.key
                    firstChild.children.forEach { secondChild ->
                        val data = secondChild.getValue(KesehatanModel::class.java)
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
        onDataChanged: (ArrayList<NifasModel>) -> Unit,
        onIsEmpty: (String) -> Unit,
        onIsLoading: (Boolean) -> Unit,
        onCancelled: (DatabaseError) -> Unit
    ) {
        onIsLoading(true)
        val database = FirebaseDatabase.getInstance().getReference("CatatanNifas")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = arrayListOf<NifasModel>()
                snapshot.children.forEach { firstChild ->
                    val firstChildKey = firstChild.key
                    firstChild.children.forEach { secondChild ->
                        val data = secondChild.getValue(NifasModel::class.java)
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
        onDataChanged: (ArrayList<AnakModel>) -> Unit,
        onIsEmpty: (String) -> Unit,
        onIsLoading: (Boolean) -> Unit,
        onCancelled: (DatabaseError) -> Unit
    ) {
        onIsLoading(true)
        val database = FirebaseDatabase.getInstance().getReference("CatatanAnak")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = arrayListOf<AnakModel>()
                snapshot.children.forEach { firstChild ->
                    val firstChildKey = firstChild.key
                    firstChild.children.forEach { secondChild ->
                        val data = secondChild.getValue(AnakModel::class.java)
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

    override fun getAllBidan(
        role: String,
        onDataChange: (List<BidanData>) -> Unit,
        onCancelled: (DatabaseError) -> Unit
    ) {
        firestore.collection("users")
            .whereEqualTo("role", role)
            .get()
            .addOnSuccessListener { documents ->
                val list = documents.mapNotNull { document ->
                    val bidan = document.toObject(BidanData::class.java)
                    bidan
                }
                onDataChange(list)
            }
            .addOnFailureListener { e ->
                onCancelled(error(e))
            }
    }
}