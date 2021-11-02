package com.example.carwash_v6.data.datasource

import com.example.carwash_sn_v1.data.request.LoginRequest
import com.example.carwash_sn_v1.data.response.LoginResponse


interface DataSource {
    fun login(req: LoginRequest): LoginResponse
}