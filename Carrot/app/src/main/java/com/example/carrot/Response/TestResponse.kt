package com.example.carrot.Response

import com.google.gson.annotations.SerializedName

data class TestResponse(
    @SerializedName("test")
    val result: List<ResultResponse>
)

data class ResultResponse(
    @SerializedName("result")
    val test: String
)