package com.example.carwash_v6.presentation.Home

import android.view.View
import android.widget.TextView
import com.example.carwash_sn_v1.ui.BaseRecyclerView
import com.example.carwash_v6.R
import com.example.carwash_v6.data.models.PackageModel

class HomeAdepter:BaseRecyclerView<PackageModel>() {
    private lateinit var tvNamePackage: TextView
    private lateinit var tvPrice: TextView
    private var listener:((PackageModel)->Unit)?=null

    override fun getLayout(): Int = R.layout.item_home

    override fun View.onBindViewHolder(data: PackageModel, position: Int) {
        tvNamePackage=findViewById(R.id.tv_namepackage)
        tvPrice=findViewById(R.id.tv_price)

        tvNamePackage.text=data.packageName
        tvPrice.text=data.packagePrice.toString()+".00"

        setOnClickListener {
            listener?.invoke(data)
        }
    }

    fun setOnClickListener(listener:(PackageModel)->Unit){
        this.listener = listener
    }
}