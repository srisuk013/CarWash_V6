package com.example.carwash_v6.data.map

import com.example.carwash_v6.data.database.CarMake
import com.example.carwash_v6.data.models.CarMakeModel
import org.jetbrains.exposed.sql.ResultRow


object CarMakeMap {
    fun toCarMakeMap(row: ResultRow) = CarMakeModel(
        car_make_id = row[CarMake.car_make_id],
        car_make_name =row[CarMake.car_make_name]
    )
}