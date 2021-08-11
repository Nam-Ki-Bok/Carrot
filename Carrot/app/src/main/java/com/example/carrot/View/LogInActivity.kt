package com.example.carrot.View

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.carrot.R

class LogInActivity : AppCompatActivity() {
    val btnJoin: Button by lazy {
        findViewById(R.id.btnJoin)
    }

    val btnStart: AppCompatButton by lazy {
        findViewById(R.id.btnStart)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnJoin.setOnClickListener {
            startActivity(Intent(this, JoinActivity::class.java))
        }

        btnStart.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}