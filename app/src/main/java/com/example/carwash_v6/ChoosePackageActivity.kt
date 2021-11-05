package com.example.carwash_v6
import android.os.Bundle
import android.widget.TextView
import com.example.carwash_v6.ui.BaseActivity

class ChoosePackageActivity : BaseActivity() {
    private lateinit var tv1: TextView
    private lateinit var tv2: TextView
    private lateinit var tv3: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_choose_package)
        val packageId = intent.getIntExtra("package",0)
        tv1=findViewById(R.id.tv_1)
        tv2=findViewById(R.id.tv_2)
        tv3=findViewById(R.id.tv_3)
      val data= dataSource.choosePackage(packageId)
        if (data != null) {
            tv1.text=data.packageName
            tv2.text=data.packageDetail
            tv3.text=data.packagePrice.toString()
        }

    }
}