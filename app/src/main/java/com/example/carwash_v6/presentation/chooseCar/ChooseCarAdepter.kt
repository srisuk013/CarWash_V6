package com.example.carwash_v6.presentation.chooseCar

import android.view.View
import android.widget.TextView
import com.example.carwash_sn_v1.ui.BaseRecyclerView
import com.example.carwash_v6.R
import com.example.carwash_v6.data.models.MyCarModel

class ChooseCarAdepter : BaseRecyclerView<MyCarModel>() {
    private lateinit var registration: TextView
    private lateinit var tvProvince: TextView
    override fun getLayout(): Int = R.layout.item_choose_car
    override fun View.onBindViewHolder(data: MyCarModel, position: Int) {
        registration=findViewById(R.id.tv_VehicleRegistration)
        tvProvince=findViewById(R.id.tv_province)
        registration.text=data.car_registration
        tvProvince.text=data.province
    }
}