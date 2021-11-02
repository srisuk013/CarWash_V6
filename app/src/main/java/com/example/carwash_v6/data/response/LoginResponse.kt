package com.example.carwash_sn_v1.data.response

data class LoginResponse(
    var success: Boolean = false,
    var message: String? = null,
    var userId: Int?=null
)
