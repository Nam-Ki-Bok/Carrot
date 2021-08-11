package com.example.carrot.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.carrot.Fragment.ChatFragment
import com.example.carrot.Fragment.HomeFragment
import com.example.carrot.Fragment.LifeFragment
import com.example.carrot.Fragment.UserFragment
import com.example.carrot.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

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
        bottomNavigation.run {
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
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

}
