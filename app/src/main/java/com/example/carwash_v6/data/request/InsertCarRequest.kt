package com.example.carwash_v6.data.request

data class InsertCarRequest(
    val userId:Int?=null,
    val car_registration:String?=null,
    val province:String?=null,
    val carMake_name:String?=null,
    val carModel_name:String?=null

)
