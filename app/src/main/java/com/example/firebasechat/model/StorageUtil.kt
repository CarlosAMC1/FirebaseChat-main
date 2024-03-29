package com.example.firebasechat.model

import com.google.common.primitives.Bytes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.lang.NullPointerException
import java.util.*

object StorageUtil {

    private val storageInstace: FirebaseStorage
    by lazy { FirebaseStorage.getInstance() }

    private val currentUserRef:StorageReference
    get() = storageInstace.reference
        .child(FirebaseAuth.getInstance().uid
            ?:throw NullPointerException("UID es nulo"))
    fun uploadProfilePhoto(imageBytes: ByteArray, onSuccess: (imagePath:String) -> Unit){

        val ref = currentUserRef.child("profilePictures/${UUID.nameUUIDFromBytes(imageBytes)}")
        ref.putBytes(imageBytes)
            .addOnSuccessListener { onSuccess(ref.path) }

    }
    fun pathToReference(path:String) = storageInstace.getReference(path)
}