package com.example.firebasechat.model

import java.nio.file.Path

data class User(val name: String,
                val bio: String,
                val profilePicturePath: String?) {
    constructor():this("","",null)

}