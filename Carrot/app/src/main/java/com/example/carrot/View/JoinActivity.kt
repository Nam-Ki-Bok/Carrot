package com.example.carrot.View

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.carrot.R

class JoinActivity: AppCompatActivity() {
    val btnLogIn: AppCompatButton by lazy {
        findViewById(R.id.btnLogIn)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        btnLogIn.setOnClickListener {
            //TODO 입력된 정보를 데이터베이스에 넣고, 다시 로그인 화면으로 돌아간다.
            startActivity(Intent(this, LogInActivity::class.java))
        }
    }
}