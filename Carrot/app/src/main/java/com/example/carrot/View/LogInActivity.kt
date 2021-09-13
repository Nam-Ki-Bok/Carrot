package com.example.carrot.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.carrot.Network.RetrofitClient
import com.example.carrot.R
import com.example.carrot.Response.UserResponse
import com.example.carrot.ResponseCode
import com.example.carrot.Service.AuthService
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class LogInActivity : AppCompatActivity() {
    private lateinit var retrofit: Retrofit
    private lateinit var loginService: AuthService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    private fun init() {
        initView()
        initRetrofit()
    }

    private fun initView() {
        btnLogIn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        //login()
        }

        tvJoin.setOnClickListener {
            join()
        }
    }

    private fun initRetrofit() {
        retrofit = RetrofitClient.getInstance()
        loginService = retrofit.create(AuthService::class.java)
    }

    private fun login() {
        val phone = etLogInPhoneNum.text.toString()
        val password = etLogInPassword.text.toString()

        val callUser = loginService.login(phone, password)
        callUser.enqueue(object: Callback <List<UserResponse>> {
            override fun onResponse(call: Call<List<UserResponse>>, response: Response<List<UserResponse>>) {
                if(response.isSuccessful && response.code() == ResponseCode.SUCCESS_POST) {

                    Log.d("LogInActivity: login(): onResponse:: ", "SUCCESS, ${response.body().toString()}")
                }
            }
            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                Log.d("LogInActivity: login(): onFailure:: ", "$t")
            }
        })
    }

    private fun join() {
        val intent = Intent(this, JoinActivity::class.java)
        startActivity(intent)
    }
}