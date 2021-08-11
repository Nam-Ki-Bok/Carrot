package com.example.carrot.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.carrot.R
import kotlinx.android.synthetic.main.activity_additem.*

class ItemSelectedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_itemselected)

        setSupportActionBar(addItemToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}