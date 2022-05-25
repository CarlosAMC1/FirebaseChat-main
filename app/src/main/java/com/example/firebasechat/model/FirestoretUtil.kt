package com.example.firebasechat.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.NullPointerException

object FirestoretUtil {
    private val firestoreInstance : FirebaseFirestore
    by lazy {FirebaseFirestore.getInstance()}
    private val currentUserDocRef:DocumentReference
    get() = firestoreInstance.document("users/${FirebaseAuth.getInstance().uid?: throw  NullPointerException("UID es nulo.")}")

    fun initCurrentUserIfFirstTime(onComplete:() -> Unit){
        currentUserDocRef.get().addOnSuccessListener { documentSnapshot ->
            if (!documentSnapshot.exists()){
                val newUser = User(FirebaseAuth.getInstance().currentUser?.displayName?:"",
                "",null)
                currentUserDocRef.set(newUser).addOnSuccessListener {
                    onComplete()
                }
            }else{
                onComplete()
            }

        }
    }
    fun updateCurrentUser(name : String = "", bio:String = "", profilePicturePath:String? = null) {
        val userFileMap = mutableMapOf<String,Any>()
        if (name.isNotBlank()) userFileMap["name"] = name
        if (bio.isNotBlank()) userFileMap["bio"] = bio
        if (profilePicturePath != null) userFileMap["profilePicturePath"] = profilePicturePath
        currentUserDocRef.update(userFileMap)
    }

    fun getCurrentUser(onComplete: (User) -> Unit){
        currentUserDocRef.get()
            .addOnSuccessListener {
                it.toObject(User::class.java)?.let {it1 -> onComplete(it1)}
            }

    }

}
