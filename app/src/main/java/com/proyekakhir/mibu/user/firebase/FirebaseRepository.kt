package com.proyekakhir.mibu.user.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore

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
                                    "uid" to user?.uid
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
}