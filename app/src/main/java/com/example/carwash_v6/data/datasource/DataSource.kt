package com.example.carwash_v6.data.datasource

import com.example.carwash_v6.data.models.*
import com.example.carwash_v6.data.request.BookingJobRequest
import com.example.carwash_v6.data.request.InsertCarRequest
import com.example.carwash_v6.data.request.LoginRequest
import com.example.carwash_v6.data.response.LoginResponse
import com.example.carwash_v6.data.request.SignupRequest
import com.example.carwash_v6.data.response.BaseResponse
import com.example.carwash_v6.data.response.SignupResponse


interface DataSource {
    fun login(req: LoginRequest): LoginResponse
    fun signup(req: SignupRequest): SignupResponse
    fun showPackage():List<PackageModel>
    fun choosePackage(id:Int):PackageModel
    fun province():List<ProvinceModel>
    fun brand():List<CarMakeModel>
    fun carModel(req:Int):List<CarModel>
    fun insertCar(req: InsertCarRequest): BaseResponse
    fun myCar(req:Int):List<MyCarModel>
    fun profile(userId: Int): ProfileModel
    fun bookingJob(req: BookingJobRequest,user:Int): BaseResponse
}