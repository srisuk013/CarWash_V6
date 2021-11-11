package com.example.carwash_v6.data.request

data class BookingJobRequest(
   val package_id :Int?=null,
   val car_id:Int?=null,
   val latitude:Double?=null,
   val longitude:Double?=null,
)
