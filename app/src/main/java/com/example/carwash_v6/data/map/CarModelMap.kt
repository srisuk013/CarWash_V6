package com.example.carwash_v6.data.map

import com.example.carwash_v6.data.database.CarMake
import com.example.carwash_v6.data.database.CarModels
import com.example.carwash_v6.data.models.CarModel
import org.jetbrains.exposed.sql.ResultRow

object CarModelMap {
    fun toCarModelMap(row: ResultRow)=CarModel(
        carModelId = row[CarModels.car_model_id],
        carModelName = row[CarModels.car_model_name],
        carMakeId = row[CarMake.car_make_id]
    )
}