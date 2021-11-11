package com.example.carwash_v6.presentation.addcar

import android.content.Intent
import android.os.Bundle
import android.widget.*
import com.example.carwash_sn_v1.ui.onItemSelected
import com.example.carwash_v6.R
import com.example.carwash_v6.data.models.CarMakeModel
import com.example.carwash_v6.data.models.CarModel
import com.example.carwash_v6.data.models.ProvinceModel
import com.example.carwash_v6.data.request.InsertCarRequest
import com.example.carwash_v6.presentation.main.MainActivity
import com.example.carwash_v6.presentation.mycar.MyCarActivity
import com.example.carwash_v6.ui.BaseActivity
import com.example.carwash_v6.ui.hideSoftKeyboard

class AddMyCarActivity : BaseActivity() {
    private lateinit var spinnerProvince: Spinner
    private lateinit var spinnerBrand: Spinner
    private lateinit var spinnerModel: Spinner
    private lateinit var province: ProvinceModel
    private lateinit var carMake: CarMakeModel
    private lateinit var carModel: CarModel
    private var carModelId: Int? = null
    private var carModelName: String? = null
    private var carMakeId: Int? = null
    private var carMakeName: String? = null
    private var provinceId: Int? = null
    private var provinceName: String? = null
    private lateinit var btSave: Button
    private lateinit var ivArrowBack:ImageView
    private lateinit var edtVehicleRegistration: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userId = mPreferences.getInt("userId", 0)
        setContentView(R.layout.activity_add_my_car)
        spinnerProvince = findViewById(R.id.spinnerProvince)
        spinnerBrand = findViewById(R.id.spinnerBrand)
        spinnerModel = findViewById(R.id.spinnerModel)

        btSave = findViewById(R.id.bt_save)
        edtVehicleRegistration = findViewById(R.id.edt_VehicleRegistration)
        ivArrowBack=findViewById(R.id.iv_arrow_back)
        ivArrowBack.setOnClickListener {
            startActivity(Intent(baseContext, MainActivity::class.java))
        }
        setSpinnerProvince()
        setSpinnerCarMake()

        btSave.setOnClickListener {
            val caRegistration = edtVehicleRegistration.text.toString().trim()
            val req = InsertCarRequest(
                userId,
                caRegistration,
                provinceName,
                carMakeName,
                carModelName
            )
            val result = dataSource.insertCar(req)
            if (result.success) {

                Toast.makeText(baseContext, result.message, Toast.LENGTH_SHORT).show()
               startActivity(Intent(baseContext, MyCarActivity::class.java))
            } else {
                Toast.makeText(baseContext, result.message, Toast.LENGTH_SHORT).show()
            }
        }
        val rootLayout =findViewById<LinearLayout>(R.id.rootLayoutaddcar)
        rootLayout.setOnClickListener { hideSoftKeyboard() }


    }

    private fun setSpinnerProvince() {
        val list = dataSource.province() as MutableList<ProvinceModel>
        spinnerProvince.adapter = ProvinceAdapter(baseContext, list)
        spinnerProvince.onItemSelected<ProvinceModel> {
            province = it
            provinceId = it.province_id
            provinceName = it.province_name
        }
    }

    private fun setSpinnerCarMake() {
        val list = dataSource.brand() as MutableList<CarMakeModel>
        spinnerBrand.adapter = CarMakeAdapter(baseContext, list)
        spinnerBrand.onItemSelected<CarMakeModel> {
            carMake = it
            carMakeId = it.car_make_id
            carMakeName = it.car_make_name
            carMakeId?.let { setSpinnerCarModel(it) }
        }
    }

    private fun setSpinnerCarModel(int: Int) {
        val list = dataSource.carModel(int) as MutableList<CarModel>
        spinnerModel.adapter = CarModelAdapter(baseContext, list)
        spinnerModel.onItemSelected<CarModel> {
            carModel = it
            carModelId = it.carModelId
            carModelName = it.carModelName
        }
    }
}
