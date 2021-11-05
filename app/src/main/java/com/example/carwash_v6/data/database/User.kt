package com.example.carwash_v6.data.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime

object User : Table("user") {

    val user_id = integer("user_id").autoIncrement()
    val username = varchar("user_username", 10)
    val password = varchar("user_password", 8)
    val telephone = varchar("user_phone", 10)
    val role_id = integer("role_id").references(Role.role_id)
    val full_name = varchar("full_name", 50)
    val latitude =double("latitude")
    val longitude =double("longitude")
    val user_date_time=datetime("user_date_time")

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(user_id, name = "user_id")
}
