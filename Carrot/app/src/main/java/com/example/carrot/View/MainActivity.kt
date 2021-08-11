package com.example.carrot.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.carrot.Fragment.ChatFragment
import com.example.carrot.Fragment.HomeFragment
import com.example.carrot.Fragment.LifeFragment
import com.example.carrot.Fragment.UserFragment
import com.example.carrot.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<HomeFragment>(R.id.fragmentContainer)
            }
        }

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeItem -> {
                    moveHome()
                    true
                }
                R.id.lifeItem -> {
                    moveLife()
                    true
                }
                R.id.chatItem -> {
                    moveChat()
                    true
                }
                R.id.userItem -> {
                    moveUser()
                    true
                }
                else -> false
            }
        }
    }

    private fun moveHome() {
        supportFragmentManager.commit {
            replace<HomeFragment>(R.id.fragmentContainer)
        }
    }
    private fun moveLife() {
        supportFragmentManager.commit {
            replace<LifeFragment>(R.id.fragmentContainer)
        }
    }
    private fun moveChat() {
        supportFragmentManager.commit {
            replace<ChatFragment>(R.id.fragmentContainer)
        }
    }
    private fun moveUser() {
        supportFragmentManager.commit {
            replace<UserFragment>(R.id.fragmentContainer)
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, fragment)
        }
    }
}
