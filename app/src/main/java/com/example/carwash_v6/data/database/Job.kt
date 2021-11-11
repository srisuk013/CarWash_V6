package com.example.carwash_v6.data.database
import org.jetbrains.exposed.sql.Table

object Job :Table("job") {
    val job_id =integer("job_id").autoIncrement()
    val user_id=integer("user_id").references(User.user_id)
    val employee_id=integer("employee_id")
    val package_id=integer("package_id").references(Package.packageId)
    val status_id=integer("status_id")
    val pay_id=integer("pay_id")
    val latitude = double("latitude")
    val longitude = double("longitude")
    val car_id =integer("car_id").references(Car.carId)
    val job_date=long("job_date")
}