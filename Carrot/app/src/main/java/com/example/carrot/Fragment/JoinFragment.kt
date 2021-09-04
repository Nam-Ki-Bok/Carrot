package com.example.carrot.Fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.carrot.Network.RetrofitClient
import com.example.carrot.R
import com.example.carrot.Response.User
import com.example.carrot.Response.UserResponse
import com.example.carrot.ResponseCode
import com.example.carrot.Service.AuthService
import kotlinx.android.synthetic.main.activity_additem.*
import kotlinx.android.synthetic.main.activity_join.*
import kotlinx.android.synthetic.main.fragment_join.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class JoinFragment : Fragment(R.layout.fragment_join) {
    private lateinit var retrofit: Retrofit
    private lateinit var joinService: AuthService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initView()
        initRetrofit()
    }

    private fun initView() {
        initToolbar()
        initName()
        initPhoneNum()
        initPassword()
        initNext()
    }

    private fun initToolbar() {
        joinToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.joinToolbarFinish -> {
                    checkUser()
                    activity?.finish()
                    true
                }
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }
        joinToolbar.setNavigationOnClickListener {
            activity?.finish()
        }
    }

    private fun initRetrofit() {
        retrofit = RetrofitClient.getInstance()
        joinService = retrofit.create(AuthService::class.java)
    }

    private fun initName() {
        etName.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (etName.text.isNullOrEmpty()) {
                    tilName.error = "이름을 입력해주세요."
                } else {
                    tilName.error = null
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

    private fun initPassword() {
        etPassword.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (etPassword.text.isNullOrEmpty()) {
                    tilPassword.error = "비밀번호를 입력해주세요."
                } else {
                    tilPassword.error = null
                }
            }
        }
    }

    private fun initNext() {
        btnNext.setOnClickListener {
            if (!etPhoneNum.text.isNullOrEmpty() && !etName.text.isNullOrEmpty() && !etPassword.text.isNullOrEmpty()) {
                checkUser()
            } else {
                setError()
            }
        }
    }


    private fun validatePhoneNum(): Boolean {
        return if (etPhoneNum.text.isNullOrEmpty()) {
            tilPhoneNum.error = "휴대폰 번호를 입력해주세요."
            false
        } else {
            val phoneNum = etPhoneNum.text.toString()
            val isValid = android.util.Patterns.PHONE.matcher(phoneNum).matches()

            if (!isValid) {
                tilPhoneNum.error = "올바른 휴대폰 번호를 입력하세요. ex)010-1234-5678"
                false
            } else {
                tilPhoneNum.error = null
                true
            }
        }
    }

    private fun checkUser() {
        val name = etName.text.toString()
        val phoneNum = etPhoneNum.text.toString()
        val password = etPassword.text.toString()
        //val callUser = joinService.isSighUp(phoneNum)

        Log.d("test","Next Button Clicked")
        moveNext(name, phoneNum, password)
        /*callUser.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    when (response.code()) {
                        ResponseCode.SUCCESS_GET -> {
                            //가입가능 유저
                            tilPhoneNum.error = null
                            moveNext(name, phoneNum, password)
                        }

                    }
                } else {
                    //이미 존재하는 유저
                    tilPhoneNum.error = "이미 가입된 휴대폰 번호입니다."
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("Join", "실패 : $t")
            }
        })*/
    }

    private fun moveNext(name: String, phoneNum: String, password: String) {
        if (!name.isNullOrEmpty() && !phoneNum.isNullOrEmpty() && !password.isNullOrEmpty()) {
            val callSignUp = joinService.signUp(name, phoneNum, password, "ROLE_ADMIN,ROLE_USER")

            callSignUp.enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    if (response.isSuccessful && response.code() == ResponseCode.SUCCESS_POST) {
                        Log.d("JoinFragment: onResponse: Success:: ", "${response.body().toString()}")
                        finish()
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.e("JoinFragment: onResponse: Failure:: ", "실패 : $t")
                }
            })
        }
    }

    private fun finish() {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.remove(this)
            ?.commit()

        Log.d("JoinFragment: finish: Success:: ", "회원가입 완료")
        Toast.makeText(context, "회원가입 완료", Toast.LENGTH_SHORT).show()
        activity?.finish()
    }

    private fun setError() {
        if (etName.text.isNullOrEmpty()) {
            tilName.error = "이름을 입력해주세요."
        }
        if (etPhoneNum.text.isNullOrEmpty()) {
            tilPhoneNum.error = "전화번호를 입력해주세요."
        }
        if (etPassword.text.isNullOrEmpty()) {
            tilPassword.error = "비밀번호를 입력해주세요."
        }
    }

}