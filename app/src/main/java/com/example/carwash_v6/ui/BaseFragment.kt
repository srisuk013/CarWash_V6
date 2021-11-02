package com.example.carwash_v6.ui

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.os.StrictMode
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.carwash_v6.data.datasource.DataSource
import com.example.carwash_v6.data.datasource.DataSourceImpl
import org.jetbrains.exposed.sql.Database

abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {

    val dataSource: DataSource = DataSourceImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userId = context?.getSharedPreferences("file", MODE_PRIVATE)?.getInt("userId", 0)
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        val host = "192.168.132.167"
        val databaseName = "carwash"
        val url = "jdbc:mysql://$host:3306/$databaseName?useUnicode=true&characterEncoding=utf-8"
        Database.connect(
            url = url,
            driver = "com.mysql.jdbc.Driver",
            user = "carwashSN",
            password = "carwashSN"
        )
    }
}
