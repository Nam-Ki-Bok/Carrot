package com.example.carrot.Response

import com.google.gson.annotations.SerializedName

data class WriteSaleResponse(
    @SerializedName("id")
    var id: Int
)