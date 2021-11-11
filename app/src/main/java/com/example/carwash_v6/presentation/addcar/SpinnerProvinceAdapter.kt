package com.example.carwash_v6.presentation.addcar
import android.content.Context
import android.view.View
import android.widget.TextView
import com.example.carwash_sn_v1.ui.BaseSpinner
import com.example.carwash_v6.R
import com.example.carwash_v6.data.models.CarMakeModel
import com.example.carwash_v6.data.models.CarModel
import com.example.carwash_v6.data.models.ProvinceModel

class ProvinceAdapter(
    context: Context,
    list: MutableList<ProvinceModel>
) : BaseSpinner<ProvinceModel>(context, list) {
    override fun getLayout(): Int = R.layout.item_spinner_base
    override fun View.onBindViewHolder(data: ProvinceModel) {
        val tvSpinnerBase = findViewById<TextView>(R.id.tvSpinnerBase)
        tvSpinnerBase.text = data.province_name
    }
}
class CarMakeAdapter(
    context: Context,
    list: MutableList<CarMakeModel>
) : BaseSpinner<CarMakeModel>(context, list) {
    override fun getLayout(): Int = R.layout.item_spinner_base
    override fun View.onBindViewHolder(data: CarMakeModel) {
        val tvSpinnerBase = findViewById<TextView>(R.id.tvSpinnerBase)
        tvSpinnerBase.text = data.car_make_name
    }
}
class CarModelAdapter(
    context: Context,
    list: MutableList<CarModel>
) : BaseSpinner<CarModel>(context, list) {
    override fun getLayout(): Int = R.layout.item_spinner_base
    override fun View.onBindViewHolder(data: CarModel) {
        val tvSpinnerBase = findViewById<TextView>(R.id.tvSpinnerBase)
        tvSpinnerBase.text = data.carModelName
    }
}