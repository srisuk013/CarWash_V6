package com.example.carwash_v6.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.edit
import com.example.carwash_sn_v1.data.request.LoginRequest
import com.example.carwash_v6.presentation.main.MainActivity
import com.example.carwash_v6.R
import com.example.carwash_v6.ui.BaseActivity
import com.example.carwash_v6.ui.hideSoftKeyboard

class LoginActivity : BaseActivity() {
    private lateinit var etPassword: EditText
    private lateinit var etUsername: EditText
    private lateinit var btSignIn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etPassword = findViewById<EditText>(R.id.et_password)
        etUsername = findViewById<EditText>(R.id.et_username)
        btSignIn = findViewById<Button>(R.id.bt_sign_in)

        btSignIn.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val req = LoginRequest(username, password)
            val result = dataSource.login(req)
            if (result.success) {
                result.userId?.let {
                    mPreferences.edit {
                        putInt("userId", it)
                    }
                }
                startActivity(Intent(baseContext, MainActivity::class.java))
            } else {
                Toast.makeText(baseContext, "ตรวจสอบรหัสอีกครั้ง", Toast.LENGTH_SHORT).show()
            }
        }
     val rootLayout =findViewById<LinearLayout>(R.id.root_layout)
        rootLayout.setOnClickListener { hideSoftKeyboard() }
    }
}