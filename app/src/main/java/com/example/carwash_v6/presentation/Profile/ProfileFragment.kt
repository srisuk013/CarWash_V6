package com.example.carwash_v6.presentation.Profile

import android.os.Bundle
import com.example.carwash_v6.R
import com.example.carwash_v6.ui.BaseFragment

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        val userId = context?.getSharedPreferences(
//            "file",
//            AppCompatActivity.MODE_PRIVATE
//        )?.getInt("userId", 0)
//        val profile = userId?.let { dataSource.profile(it) }
//        tv_full_name.text = profile?.name
//        tv_phone.text = profile?.telephone
    }

}