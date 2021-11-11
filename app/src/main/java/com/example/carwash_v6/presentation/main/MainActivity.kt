package com.example.carwash_v6.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.carwash_v6.presentation.mycar.MyCarActivity
import com.example.carwash_v6.presentation.Profile.ProfileFragment
import com.example.carwash_v6.R
import com.example.carwash_v6.presentation.History.HistoryFragment
import com.example.carwash_v6.presentation.Home.HomeFragment
import com.example.carwash_v6.ui.BaseActivity
import com.example.carwash_v6.ui.startActivityActionDial
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottom_navigation=findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val toolbar =findViewById<Toolbar>(R.id.toolbar)
        bottom_navigation.setOnNavigationItemSelectedListener(navListener)
        if (savedInstanceState == null)
            replaceFragment(HomeFragment())
        toolbar.title = ""
        setSupportActionBar(toolbar)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            fragment
        ).commit()
    }

    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val selectedFragment: Fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_history -> HistoryFragment()
                else -> ProfileFragment()
            }
            replaceFragment(selectedFragment)
            true
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.option_change_mycar ->startActivity(Intent(baseContext, MyCarActivity::class.java))
            R.id.option_logout->finish()
            R.id.option_contact_admin -> dialogContactAdmin { startActivityActionDial() }

        }
        return super.onOptionsItemSelected(item)
    }
    protected fun dialogContactAdmin(
        @StringRes title: Int = R.string.contact_admin,
        @StringRes message: Int = R.string.contact_admin_message,
        contactAdmin: () -> Unit
    ) = AlertDialog.Builder(this).apply {
        setTitle(title)
        setMessage(message)
        setPositiveButton(android.R.string.ok) { _, _ ->
            contactAdmin.invoke()
        }
        setNegativeButton(android.R.string.cancel) { dialog, _ ->
            dialog.dismiss()
        }
        setCancelable(false)
        show()
    }


}