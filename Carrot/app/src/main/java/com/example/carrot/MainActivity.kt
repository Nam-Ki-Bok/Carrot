package com.example.carrot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(p0: MenuItem) : Boolean {
        when(p0.itemId) {
            R.id.homeItem -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentFrame, HomeFragment())
                transaction.commit()
                return true
            }
        }

        return false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
