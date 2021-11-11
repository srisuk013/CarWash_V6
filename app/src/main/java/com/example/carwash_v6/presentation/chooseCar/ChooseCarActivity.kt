package com.example.carwash_v6.presentation.chooseCar

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carwash_v6.presentation.choosemap.ChooseMapActivity
import com.example.carwash_v6.ChoosePackageActivity
import com.example.carwash_v6.R
import com.example.carwash_v6.ui.BaseActivity

class ChooseCarActivity : BaseActivity() {
    private lateinit var recyclerViewChooseCar: RecyclerView
    private lateinit var iv_arrow_back:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_car)
        val userId = mPreferences.getInt("userId", 0)
        val packageId = intent.getIntExtra("package", 0)
        val adt = ChooseCarAdepter()
        iv_arrow_back=findViewById(R.id.iv_arrow_back)
        recyclerViewChooseCar = findViewById(R.id.recycler_view_choosecar)
        recyclerViewChooseCar.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adt
        }
        val data=dataSource.myCar(userId)
        adt.setList(data)
        adt.onClick ={
            val intent = Intent(baseContext, ChooseMapActivity::class.java).apply {
                putExtra("carId",it.carId)
                putExtra("packageId",packageId)
            }
            startActivity(intent)
        }
        iv_arrow_back.setOnClickListener {
            startActivity(Intent(baseContext, ChoosePackageActivity::class.java))
        }
    }
}