package com.example.carwash_v6.data.map

import com.example.carwash_v6.data.database.User
import com.example.carwash_v6.data.models.ProfileModel
import org.jetbrains.exposed.sql.ResultRow

object ProfileMap {
    fun toProfileMap(row: ResultRow)=ProfileModel(
        full_name = row[User.full_name],
        telephone = row[User.telephone]
    )
}