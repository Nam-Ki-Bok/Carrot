package com.example.carrot.View

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.carrot.Network.RetrofitClient
import com.example.carrot.R
import com.example.carrot.Service.AuthService
import kotlinx.android.synthetic.main.activity_join.*
import retrofit2.Retrofit

class JoinActivity : AppCompatActivity() {
    private lateinit var retrofit: Retrofit
    private lateinit var joinService: AuthService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        init()
    }

    private fun init() {
        initView()
        initRetrofit()
    }

    private fun initView() {
        initName()
        initPhoneNum()
        initPassword()
        initNext()
    }

    private fun initRetrofit() {
        retrofit = RetrofitClient.getInstance()
        joinService = retrofit.create(AuthService::class.java)
    }

    private fun initName() {
        etName.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (etName.text.isNullOrEmpty()) {
                    etName.error = "이름을 입력해주세요."
                } else {
                    etName.error = null
                }

            }
        }
    }

    private fun initPhoneNum() {
        etPhoneNum.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                validatePhoneNum()
            }
        }
    }

    private fun validatePhoneNum() : Boolean {
        return if (etPhoneNum.text.isNullOrEmpty()) {
            etPhoneNum.error = "휴대폰 번호를 입력해주세요."
            false
        } else {
            val phoneNum = etPhoneNum.text.toString()
            val isValid = android.util.Patterns.PHONE.matcher(phoneNum).matches()

            if(!isValid) {

            }
        }
    }
}