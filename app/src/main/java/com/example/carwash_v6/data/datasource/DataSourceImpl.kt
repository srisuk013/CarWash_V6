package com.example.carwash_v6.data.datasource

import com.example.carwash_v6.data.database.User
import com.example.carwash_sn_v1.data.request.LoginRequest
import com.example.carwash_sn_v1.data.response.LoginResponse
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object DataSourceImpl : DataSource {
    override fun login(req: LoginRequest): LoginResponse {
        val response = LoginResponse()

        if (req.username.isBlank()) {
            response.message = "Username empty"
        } else if (req.password.isBlank()) {
            response.message = "Password empty"
        } else {
            val result = transaction {
                addLogger(StdOutSqlLogger)
                User.select { User.username eq req.username }
                    .andWhere { User.password eq req.password }
                    .count()
                    .toInt()
            }
            if (result == 0) {
                response.success = false
                response.message = "Password incorrect"
            } else {
                val userId = transaction {
                    User.select { User.username eq req.username }
                        .andWhere { User.password eq req.password }
                        .map { it[User.user_id] }
                        .single()
                }
                response.userId = userId
                response.success = true
                response.message = "Login success"
            }
        }
        return response
    }

}