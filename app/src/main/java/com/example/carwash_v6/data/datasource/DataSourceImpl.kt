package com.example.carwash_v6.data.datasource

import com.example.carwash_v6.data.database.Package
import com.example.carwash_v6.data.database.User
import com.example.carwash_v6.data.map.PackageMap
import com.example.carwash_v6.data.models.PackageModel
import com.example.carwash_v6.data.request.LoginRequest
import com.example.carwash_v6.data.response.LoginResponse
import com.example.carwash_v6.data.request.SignupRequest
import com.example.carwash_v6.data.response.SignupResponse
import org.jetbrains.exposed.sql.*
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
    override fun signup(req: SignupRequest): SignupResponse {
        val response = SignupResponse()
        if (req.username.isBlank()) {
            response.message = "กรุณากรอกUsername"
        } else if (req.username.length < 8) {
            response.message = "Usernameต้องมีมากว่าเท่ากับ8ตัวอักษร"
        } else if (req.password.isBlank()) {
            response.message = "กรุณากรอกPassword"
        } else if (req.password.length < 8) {
            response.message = "Passwordต้องมีมากว่าเท่ากับ8ตัวอักษร"
        }else if (req.re_password.isBlank()) {
            response.message = "กรุณากรอกPassword"
        }else if (req.password != req.re_password) {
            response.message = "กรุณากรอกPasswordทั้งสองช่องให้ตรง"
        }else if (req.full_name.isBlank()) {
            response.message = "กรุณากรอกชื่อ"
        } else if (req.telephone.isBlank()) {
            response.message = "กรุณากรอกเบอร์"
        }  else if (req.telephone.length != 10) {
            response.message = "กรุณาตรวจสอบเบอร์โทร"
        } else {
            val statement = transaction {
                addLogger(StdOutSqlLogger)
                User.insert {
                    it[username] = req.username
                    it[full_name] = req.full_name
                    it[role_id] = 1
                    it[latitude] = req.latitude!!
                    it[longitude] = req.longitude!!
                    it[password] = req.password
                    it[telephone] = req.telephone
                    it[user_date_time] = req.user_date_time!!
                }
            }
            val result = statement.resultedValues?.size == 1
            response.success = result
            response.message = "Signup success"
        }
        return response
    }

    override fun showPackage():List<PackageModel>{
        return transaction{
            addLogger(StdOutSqlLogger)
           Package
               .slice(
                   Package.packageId,
                   Package.packageName,
                   Package.packagePrice,
                   Package.packageDetail,
               )
               .selectAll()
               .map { PackageMap.toPackageMap(it) }
        }

    }
    override fun choosePackage(id:Int):PackageModel{
        return transaction {
            addLogger(StdOutSqlLogger)
            Package
                .slice(
                    Package.packageId,
                    Package.packageDetail,
                    Package.packageName,
                    Package.packagePrice,
                )
                .select { Package.packageId eq id }
                .map { PackageMap.toPackageMap(it) }
                .single()
        }
    }

}
