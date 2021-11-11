package com.example.carwash_v6.data.map

import com.example.carwash_v6.data.database.Province
import com.example.carwash_v6.data.models.ProvinceModel
import org.jetbrains.exposed.sql.ResultRow

object ProvinceMap {
    fun toProvinceMap(row: ResultRow) = ProvinceModel(
        province_id = row[Province.province_id],
        province_name = row[Province.province_name]

    )
}


