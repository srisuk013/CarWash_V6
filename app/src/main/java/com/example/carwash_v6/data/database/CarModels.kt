package com.example.carwash_v6.data.database

import org.jetbrains.exposed.sql.Table

object CarModels : Table("carmodel") {
    val car_model_id=integer("car_model_id").autoIncrement()
    val car_model_name=varchar("car_model_name",50)
    val car_make_id=integer("car_make_id").references(CarMake.car_make_id)
    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(CarModels.car_model_id, name = "car_model_id")
}