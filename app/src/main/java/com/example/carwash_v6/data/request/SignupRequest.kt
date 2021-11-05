package com.example.carwash_v6.data.request

import org.joda.time.DateTime

data class SignupRequest(
    val username:String,
    val password:String,
    val re_password:String,
    val telephone:String,
    val full_name:String,
    val role_id: Int?=null,
    val latitude: Double?=null,
    val longitude: Double?=null,
    val user_date_time: DateTime?=null,
)
