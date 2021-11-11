package com.example.carwash_v6
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.carwash_v6.presentation.chooseCar.ChooseCarActivity
import com.example.carwash_v6.presentation.main.MainActivity
import com.example.carwash_v6.ui.BaseActivity

class ChoosePackageActivity : BaseActivity() {
    private lateinit var tv1: TextView
    private lateinit var tv2: TextView
    private lateinit var tv3: TextView
    private lateinit var ivArrowBack:ImageView
    private lateinit var btnChoosePackage:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_choose_package)
        val packageId = intent.getIntExtra("package",0)
        tv1=findViewById(R.id.tv_1)
        tv2=findViewById(R.id.tv_2)
        tv3=findViewById(R.id.tv_3)
        ivArrowBack=findViewById(R.id.iv_arrow_back)
        btnChoosePackage=findViewById(R.id.btn_ChoosePackage)
        ivArrowBack.setOnClickListener {
            startActivity(Intent(baseContext, MainActivity::class.java))
        }
      val data= dataSource.choosePackage(packageId)

        if (data != null) {
            tv1.text=data.packageName
            tv2.text=data.packageDetail
            tv3.text= "฿ " +data.packagePrice.toString()
        }
        btnChoosePackage.setOnClickListener {
            val intent = Intent(baseContext, ChooseCarActivity::class.java).apply {
                putExtra("package",data.packageId)
            }
            startActivity(intent)
        }

    }
}