package com.example.carrot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.carrot.Fragment.ChatFragment
import com.example.carrot.Fragment.HomeFragment
import com.example.carrot.Fragment.LifeFragment
import com.example.carrot.Fragment.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val HomeFragment by lazy {
        HomeFragment()
    }
    private val LifeFragment by lazy {
        LifeFragment()
    }
    private val ChatFragment by lazy {
        ChatFragment()
    }
    private val UserFragment by lazy {
        UserFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigationBar()
    }

    private fun initNavigationBar() {
        navigationBar.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.homeItem -> {
                        changeFragment(HomeFragment)
                    }
                    R.id.lifeItem -> {
                        changeFragment(LifeFragment)
                    }
                    R.id.chatItem -> {
                        changeFragment(ChatFragment)
                    }
                    R.id.userItem -> {
                        changeFragment(UserFragment)
                    }
                }
                true
            }
            selectedItemId = R.id.homeItem
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentFrame, fragment)
            .commit()
    }
}
