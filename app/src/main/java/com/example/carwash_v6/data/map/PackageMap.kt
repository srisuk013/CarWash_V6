package com.example.carwash_v6.data.map

import com.example.carwash_v6.data.database.Package
import com.example.carwash_v6.data.models.PackageModel
import org.jetbrains.exposed.sql.ResultRow

object PackageMap {
    fun toPackageMap(row: ResultRow)=PackageModel(
        packageId = row[Package.packageId],
        packageName = row[Package.packageName],
        packagePrice = row[Package.packagePrice],
        packageDetail = row[Package.packageDetail]
    )
}