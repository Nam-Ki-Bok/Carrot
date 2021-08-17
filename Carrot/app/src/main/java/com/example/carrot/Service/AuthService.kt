package com.example.carrot.Service

import com.example.carrot.Response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface AuthService {
    @GET("auth/is-sigh-up")
    fun isSighUp (
        @Query("email") email: String
    ) : Call<Void>

    @FormUrlEncoded
    @POST("auth/sign-up")
    fun signUp(
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("password") password: String
    ) : Call<UserResponse>

    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<UserResponse>
}