package com.example.carwash_v6.presentation.signup
import android.content.Intent
import android.os.Bundle
import android.widget.*
import com.example.carwash_v6.R
import com.example.carwash_v6.data.request.SignupRequest
import com.example.carwash_v6.presentation.login.LoginActivity
import com.example.carwash_v6.ui.BaseActivity
import com.example.carwash_v6.ui.hideSoftKeyboard
import org.joda.time.DateTime

class SignUpActivity : BaseActivity() {
    private lateinit var ivArrowBack: ImageView
    private lateinit var etPassword: EditText
    private lateinit var etUsername: EditText
    private lateinit var etRePassword: EditText
    private lateinit var etFullName: EditText
    private lateinit var etPhone: EditText
    private lateinit var btSignIn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        ivArrowBack=findViewById(R.id.iv_arrow_back)
        etUsername=findViewById(R.id.et_username)
        etPassword=findViewById(R.id.et_password)
        etRePassword=findViewById(R.id.et_repassword)
        etFullName=findViewById(R.id.et_fullname)
        etPhone=findViewById(R.id.et_phone)
        btSignIn=findViewById(R.id.bt_sign_in)

        btSignIn.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val telephone = etPhone.text.toString().trim()
            val rePassword = etRePassword.text.toString().trim()
            val fullName = etFullName.text.toString().trim()
            val req =SignupRequest(username,password,rePassword,telephone,fullName
                ,role_id = 1,latitude = 0.0,longitude = 0.0,user_date_time = DateTime.now())
            val result = dataSource.signup(req)
            if(result.success){
                startActivity(Intent(baseContext, LoginActivity::class.java))
            }else{
                Toast.makeText(baseContext, result.message, Toast.LENGTH_SHORT).show()
            }
        }
        ivArrowBack.setOnClickListener {
            startActivity(Intent(baseContext, LoginActivity::class.java))
        }
        val rootLayout =findViewById<LinearLayout>(R.id.root_layout)
        rootLayout.setOnClickListener { hideSoftKeyboard() }

    }
}