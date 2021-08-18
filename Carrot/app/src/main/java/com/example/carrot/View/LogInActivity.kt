package com.example.carrot.View

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.carrot.R
import kotlinx.android.synthetic.main.activity_login.*

class LogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogIn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        tvJoin.setOnClickListener {
            startActivity(Intent(this, JoinActivity::class.java))
        }
    }
}