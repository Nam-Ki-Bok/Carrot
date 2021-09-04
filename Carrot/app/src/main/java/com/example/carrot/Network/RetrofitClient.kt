package com.example.carrot.Network

import com.example.carrot.R
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var instance: Retrofit? = null
    private val gson = GsonBuilder().setLenient().create()

//    private const val BASE_URL = "http://ec2-3-34-172-246.ap-northeast-2.compute.amazonaws.com:8080/"
    private const val BASE_URL = "http://10.0.2.2:8080/"

    fun getInstance(): Retrofit {
        if(instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        return instance!!
    }
}