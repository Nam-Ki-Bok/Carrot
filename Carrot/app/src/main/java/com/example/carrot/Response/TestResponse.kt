package com.example.carrot.Response

import com.google.gson.annotations.SerializedName

data class TestResponse(
    @SerializedName("test")
    var test: String
)