package com.example.carwash_v6.data.datasource

import com.example.carwash_v6.data.models.PackageModel
import com.example.carwash_v6.data.request.LoginRequest
import com.example.carwash_v6.data.response.LoginResponse
import com.example.carwash_v6.data.request.SignupRequest
import com.example.carwash_v6.data.response.SignupResponse


interface DataSource {
    fun login(req: LoginRequest): LoginResponse
    fun signup(req: SignupRequest): SignupResponse
    fun showPackage():List<PackageModel>
    fun choosePackage(id:Int):PackageModel
}