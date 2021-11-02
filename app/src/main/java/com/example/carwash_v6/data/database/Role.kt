package com.example.carwash_sn_v1.data.database

import com.example.carwash_v6.data.database.User
import org.jetbrains.exposed.sql.Table

object Role : Table("role") {
    val role_id = integer("role_id").autoIncrement()
    val role_name =varchar("role_name",50)
    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(User.role_id, name = "role_id")
}