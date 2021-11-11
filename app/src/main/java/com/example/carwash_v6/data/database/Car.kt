package com.example.carwash_v6.data.database

import org.jetbrains.exposed.sql.Table

object Car:Table("car") {
    val carId=integer("car_id").autoIncrement()
    val userId=integer("user_id").references(User.user_id)
    val car_registration=varchar("car_registration",50)
    val province=varchar("province",50)
    val car_make_name=varchar("car_make_name",50)
    val car_model_name=varchar("car_model_name",50 )
    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(Car.carId, name = "carId")
}