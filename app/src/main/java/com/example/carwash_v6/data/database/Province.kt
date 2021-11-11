package com.example.carwash_v6.data.database

import org.jetbrains.exposed.sql.Table

object Province :Table("province"){
    val province_id=integer("province_id").autoIncrement()
    val province_name=varchar("province_name",100)
    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(Province.province_id, name = "province_id")
}