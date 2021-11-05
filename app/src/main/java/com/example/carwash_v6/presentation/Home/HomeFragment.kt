package com.example.carwash_v6.presentation.Home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carwash_v6.ChoosePackageActivity
import com.example.carwash_v6.R
import com.example.carwash_v6.ui.BaseFragment

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private lateinit var recyclerPackage: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adt = HomeAdepter()
        recyclerPackage = view.findViewById(R.id.recycler_package)
        recyclerPackage.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adt
        }
        val data = dataSource.showPackage()
        adt.setList(data)

        adt.setOnClickListener {model->
//            Log.d("TAG", "onViewCreated: ${model.packageId}")
            val intent = Intent(context,ChoosePackageActivity::class.java).apply {
                putExtra("package",model.packageId)
            }
            startActivity(intent)
//            Toast.makeText(context, "${model.packageName}", Toast.LENGTH_SHORT).show()
        }

    }
}