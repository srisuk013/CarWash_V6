package com.example.carwash_v6.presentation.mycar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carwash_v6.presentation.addcar.AddMyCarActivity
import com.example.carwash_v6.R
import com.example.carwash_v6.presentation.Home.HomeAdepter
import com.example.carwash_v6.presentation.main.MainActivity
import com.example.carwash_v6.ui.BaseActivity

class MyCarActivity : BaseActivity() {
    private lateinit var ivArrowBack: ImageView
    private lateinit var ivAddcar:ImageView
    private lateinit var recyclerViewMyCar:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userId = mPreferences.getInt("userId", 0)
        setContentView(R.layout.activity_my_car)
        ivArrowBack=findViewById(R.id.iv_arrow_back)
        ivAddcar=findViewById(R.id.iv_addcar)
        val adt = MyCarAdepter()
        recyclerViewMyCar = findViewById(R.id.recycler_view_mycar)
        recyclerViewMyCar.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adt
        }
        val data=dataSource.myCar(userId)
        adt.setList(data)
        ivArrowBack.setOnClickListener {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }
        ivAddcar.setOnClickListener {
            val intent = Intent(baseContext, AddMyCarActivity::class.java)
            startActivity(intent)
        }
    }
}