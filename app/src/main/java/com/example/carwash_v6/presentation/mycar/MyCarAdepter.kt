package com.example.carwash_v6.presentation.mycar
import android.view.View
import android.widget.TextView
import com.example.carwash_sn_v1.ui.BaseRecyclerView
import com.example.carwash_v6.R
import com.example.carwash_v6.data.models.MyCarModel

class MyCarAdepter:BaseRecyclerView<MyCarModel>(){
    private lateinit var tvVehicleRegistration: TextView
    private lateinit var tvBrand: TextView
    private lateinit var tvModel: TextView
    override fun getLayout(): Int =R.layout.item_mycar
    override fun View.onBindViewHolder(data: MyCarModel, position: Int) {
        tvVehicleRegistration =findViewById(R.id.tv_VehicleRegistration)
        tvBrand =findViewById(R.id.tv_Brand)
        tvModel =findViewById(R.id.tv_Model)
        tvVehicleRegistration.text=data.car_registration
        tvBrand.text=data.car_make_name
        tvModel.text=data.car_model_name
    }
}