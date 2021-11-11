package com.example.carwash_v6.data.database

import org.jetbrains.exposed.sql.Table

object CarMake : Table("carmake") {
    val car_make_id = integer("car_make_id").autoIncrement()
    val car_make_name = varchar("car_make_name", 50)
    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(CarMake.car_make_id, name = "car_make_id")

}