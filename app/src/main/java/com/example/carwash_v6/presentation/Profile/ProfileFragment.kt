package com.example.carwash_v6.presentation.Profile

import android.content.Context
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.example.carwash_v6.R
import com.example.carwash_v6.ui.BaseFragment

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    private lateinit var tvFullName:TextView
    private lateinit var tvPhone:TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = context?.getSharedPreferences("file", Context.MODE_PRIVATE)?.getInt("userId", 0)
        tvFullName=view.findViewById(R.id.tv_full_name)
        tvPhone=view.findViewById(R.id.tv_phone)
        val data=dataSource.profile(userId!!)
        tvFullName.text=data.full_name
        tvPhone.text=data.telephone



    }
}

