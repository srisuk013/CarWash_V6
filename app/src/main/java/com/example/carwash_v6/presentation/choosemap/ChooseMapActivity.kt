package com.example.carwash_v6.presentation.choosemap

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.carwash_v6.ChoosePackageActivity
import com.example.carwash_v6.R
import com.example.carwash_v6.data.database.Job.latitude
import com.example.carwash_v6.data.request.BookingJobRequest
import com.example.carwash_v6.presentation.chooseCar.ChooseCarActivity
import com.example.carwash_v6.presentation.main.MainActivity
import com.example.carwash_v6.ui.BaseLocationActivity
import com.example.carwash_v6.util.awaitLastLocation
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch


class ChooseMapActivity : BaseLocationActivity() {
    var mBookingJobRequest: BookingJobRequest? = null

    private var mGoogleMap: GoogleMap? = null
    private var mIsFlagMoveCamera: Boolean = true
    private var mMarkerMyLocation: Marker? = null
    private var mMarkerCustomer: Marker? = null
    private lateinit var iv_arrow_back: ImageView
    private lateinit var bt_choose_location:Button

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_map)

//        val packages = intent.getParcelableExtra<PackageCar>("ChooseMapActivity")
        val carId = intent.getIntExtra("carId", 0)
        val packageId=intent.getIntExtra("packageId", 0)
        val userId = mPreferences.getInt("userId", 0)
//       val vehicleRegistration=intent.getStringExtra("vehicleRegistration")
//       val province=intent.getStringExtra("province")
        mBookingJobRequest = BookingJobRequest(package_id =packageId,car_id = carId )

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

//        setToolbar(toolbar)

        val locationProviderClient = LocationServices
            .getFusedLocationProviderClient(this)

//        map_fragment.onCreate(savedInstanceState)
        (supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment).onCreate(
            savedInstanceState
        )


        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            lifecycleScope.launch {
                googleMap.isMyLocationEnabled = true
                googleMap.setMinZoomPreference(12F)
                googleMap.setMaxZoomPreference(16F)

                val location = locationProviderClient.awaitLastLocation()
                val latLng = LatLng(location.latitude, location.longitude)
                val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14F)
                googleMap.animateCamera(cameraUpdate)

                setMarkerChooseMap(latLng, googleMap)

                mBookingJobRequest = mBookingJobRequest?.copy(
                    latitude = location.latitude,
                    longitude = location.longitude
                )
            }
        }
        iv_arrow_back = findViewById(R.id.iv_arrow_back)
        iv_arrow_back.setOnClickListener {
            val intent = Intent(baseContext, ChooseCarActivity::class.java)
            startActivity(intent);
        }

        mapFragment.getMapAsync { googleMap ->
            googleMap.setOnMapClickListener { latLng ->
                mBookingJobRequest = mBookingJobRequest?.copy(
                    latitude = latLng.latitude,
                    longitude = latLng.longitude
                )
                Toast.makeText(
                    baseContext,
                    "${latLng.latitude}, ${latLng.longitude}",
                    Toast.LENGTH_SHORT
                ).show()
                setMarkerChooseMap(latLng, googleMap)
            }
        }
        bt_choose_location=findViewById(R.id.bt_choose_location)
        bt_choose_location.setOnClickListener {
         dataSource.bookingJob(mBookingJobRequest!!,userId)
            startActivity(Intent(baseContext, MainActivity::class.java))
        }
    }

    private fun setMarkerChooseMap(latLng: LatLng, googleMap: GoogleMap) {
        mMarkerCustomer?.remove()
        val marker = MarkerOptions().apply {
            position(latLng)
            icon(BitmapDescriptorFactory.defaultMarker())
        }
        mMarkerCustomer = googleMap.addMarker(marker)
    }
}