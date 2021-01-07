package com.harrisonbacordo.flatmate.data.models

data class User(
    val id: String = "",
    val flatId: String = "",
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val fullName: String = "",
    val profPicUri: String = "",
    val nudgeCount: Int = 0,
)