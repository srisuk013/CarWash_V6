package com.example.carwash_v6.data.database

import org.jetbrains.exposed.sql.Table

object Package :Table("package") {
    val packageId=integer("package_id").autoIncrement()
    val packageName=varchar("package_name",50)
    val packagePrice=integer("package_price")
    val packageImage=varchar("package_image",100)
    val packageDetail=varchar("package_detail",100)
    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(Package.packageId, name = "package_id")
}