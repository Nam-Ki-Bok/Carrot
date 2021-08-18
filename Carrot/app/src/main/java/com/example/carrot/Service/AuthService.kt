package com.example.carrot.Service

import com.example.carrot.Response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface AuthService {
    @GET("api/user/join")
    fun isSighUp (
        @Query("phone") phone: String
    ) : Call<Void>

    @FormUrlEncoded
    @POST("api/user/join")
    fun signUp(
        @Field("name") name: String,
        @Field("phone") phone: String,
        @Field("password") password: String
    ) : Call<UserResponse>

    @FormUrlEncoded
    @POST("api/user/login")
    fun login(
        @Field("phone") phone: String,
        @Field("password") password: String
    ) : Call<UserResponse>
}