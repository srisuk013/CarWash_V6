package com.example.carwash_v6.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.carwash_v6.R
import com.example.carwash_v6.presentation.login.LoginActivity
import com.example.carwash_v6.ui.BaseActivity
import com.example.carwash_v6.ui.hideSoftKeyboard

class SignUpActivity : BaseActivity() {
    private lateinit var ivArrowBack: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        ivArrowBack=findViewById<ImageView>(R.id.iv_arrow_back)

        ivArrowBack.setOnClickListener {
            startActivity(Intent(baseContext, LoginActivity::class.java))
        }
        val rootLayout =findViewById<LinearLayout>(R.id.root_layout)
        rootLayout.setOnClickListener { hideSoftKeyboard() }

    }
}