package com.example.carrot.Response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class UserResponse(
    @SerializedName("token")
    var token: String
)

@Parcelize
data class User(
    @SerializedName("id")
    @Expose
    var id: Int = 0,

    @SerializedName("name")
    @Expose
    var name: String = "",

    @SerializedName("phone")
    @Expose
    var phone: String = ""

) : Parcelable
//주석