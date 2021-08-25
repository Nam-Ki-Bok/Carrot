package com.example.carrot.Response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class UserResponse(
    @SerializedName("token")
    var token: String
)