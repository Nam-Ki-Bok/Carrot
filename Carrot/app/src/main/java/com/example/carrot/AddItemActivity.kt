package com.example.carrot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_additem.*
import kotlinx.android.synthetic.main.activity_main.*

class AddItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_additem)

        setSupportActionBar(addItemToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}