package com.example.carwash_v6.data.map

import com.example.carwash_v6.data.database.Car
import com.example.carwash_v6.data.models.MyCarModel
import org.jetbrains.exposed.sql.ResultRow

object MyCarMap {
    fun toMyCarMap(row: ResultRow)=MyCarModel(
        car_make_name = row[Car.car_make_name],
        car_model_name = row[Car.car_model_name],
        car_registration = row[Car.car_registration],
        carId = row[Car.carId],
        province = row[Car.province]
    )
}