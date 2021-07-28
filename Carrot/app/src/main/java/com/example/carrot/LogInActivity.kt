package com.example.carrot

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LogInActivity : AppCompatActivity() {
    val btnJoin: Button by lazy {
        findViewById(R.id.btnJoin)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnJoin.setOnClickListener {
            startActivity(Intent(this, JoinActivity::class.java))
        }
    }
}