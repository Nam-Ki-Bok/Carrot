package com.example.carrot.Response

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("successString")
    val successString: String
)