package com.example.carwash_v6.data.datasource

import com.example.carwash_v6.data.database.*
import com.example.carwash_v6.data.map.*
import com.example.carwash_v6.data.models.*
import com.example.carwash_v6.data.request.BookingJobRequest
import com.example.carwash_v6.data.request.InsertCarRequest
import com.example.carwash_v6.data.request.LoginRequest
import com.example.carwash_v6.data.response.LoginResponse
import com.example.carwash_v6.data.request.SignupRequest
import com.example.carwash_v6.data.response.BaseResponse
import com.example.carwash_v6.data.response.SignupResponse
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

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
            response.message = "???????????????????????????Username"
        } else if (req.username.length < 8) {
            response.message = "Username?????????????????????????????????????????????????????????8????????????????????????"
        } else if (req.password.isBlank()) {
            response.message = "???????????????????????????Password"
        } else if (req.password.length < 8) {
            response.message = "Password?????????????????????????????????????????????????????????8????????????????????????"
        } else if (req.re_password.isBlank()) {
            response.message = "???????????????????????????Password"
        } else if (req.password != req.re_password) {
            response.message = "???????????????????????????Password???????????????????????????????????????????????????"
        } else if (req.full_name.isBlank()) {
            response.message = "???????????????????????????????????????"
        } else if (req.telephone.isBlank()) {
            response.message = "??????????????????????????????????????????"
        } else if (req.telephone.length != 10) {
            response.message = "????????????????????????????????????????????????????????????"
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

    override fun showPackage(): List<PackageModel> {
        return transaction {
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

    override fun choosePackage(id: Int): PackageModel {
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

    override fun province(): List<ProvinceModel> {
        return transaction {
            addLogger(StdOutSqlLogger)
            Province
                .slice(
                    Province.province_id,
                    Province.province_name
                )
                .selectAll()
                .map { ProvinceMap.toProvinceMap(it) }
        }
    }

    override fun brand(): List<CarMakeModel> {
        return transaction {
            addLogger(StdOutSqlLogger)
            CarMake
                .slice(
                    CarMake.car_make_id,
                    CarMake.car_make_name
                )
                .selectAll()
                .map { CarMakeMap.toCarMakeMap(it) }
        }
    }

    override fun carModel(req: Int): List<CarModel> {
        return transaction {
            addLogger(StdOutSqlLogger)
            (CarModels innerJoin CarMake)
                .slice(
                    CarModels.car_model_id,
                    CarModels.car_model_name,
                    CarMake.car_make_id
                )
                .select { CarMake.car_make_id eq req }
                .map { CarModelMap.toCarModelMap(it) }
        }
    }

    override fun insertCar(req: InsertCarRequest): BaseResponse {
        val response = BaseResponse()
        if (req.car_registration!!.isBlank()) {
            response.message = "???????????????????????????????????????????????????????????????????????????"
        } else {
            val statement = transaction {
                addLogger(StdOutSqlLogger)
                Car.insert {
                    it[userId] = req.userId!!.toInt()
                    it[car_registration] = req.car_registration
                    it[car_make_name] = req.carMake_name!!
                    it[car_model_name] = req.carModel_name!!
                    it[province] = req.province!!
                }
            }
            val result = statement.resultedValues?.size == 1
            response.success = result
            response.message = "Insert success"
        }
        return response
    }

    override fun myCar(req: Int): List<MyCarModel> {
        return transaction {
            addLogger(StdOutSqlLogger)
            (Car)
                .slice(
                    Car.carId,
                    Car.car_registration,
                    Car.province,
                    Car.car_make_name,
                    Car.car_model_name
                ).select { Car.userId eq req }
                .map { MyCarMap.toMyCarMap(it) }
        }
    }

    override fun profile(userId: Int): ProfileModel {
        return transaction {
            addLogger(StdOutSqlLogger)
            User
                .slice(
                    User.full_name,
                    User.telephone,
                )
                .select { User.user_id eq userId }
                .map { ProfileMap.toProfileMap(it) }
                .single()
        }
    }

    override fun bookingJob(req: BookingJobRequest,user:Int): BaseResponse {
        val response = BaseResponse()
        val statement = transaction {
            addLogger(StdOutSqlLogger)
            Job.insert {
                it[Job.user_id] =user
                it[Job.car_id] = req.car_id!!
                it[Job.employee_id] = 0
                it[Job.latitude] = req.latitude!!
                it[Job.longitude] = req.longitude!!
                it[Job.package_id] = req.package_id!!
                it[Job.job_date] =DateTime.now().millis
                it[Job.pay_id] = 0
                it[Job.status_id] = 0
            }
        }
        val result = statement.resultedValues?.size == 1
        response.success = result
        response.message = "Insert success"
        return response
    }

}
