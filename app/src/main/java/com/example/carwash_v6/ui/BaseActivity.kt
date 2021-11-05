package com.example.carwash_v6.ui

import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import com.example.carwash_v6.data.datasource.DataSource
import com.example.carwash_v6.data.datasource.DataSourceImpl
import org.jetbrains.exposed.sql.Database

abstract class BaseActivity : AppCompatActivity() {
    val dataSource: DataSource = DataSourceImpl

    protected val mPreferences by lazy {
        getSharedPreferences("file", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userId = mPreferences.getInt("userId", 0)
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        val host = "192.168.27.167"
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