package com.example.carrot.Response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class UserResponse(
    @SerializedName("user")
    var user: List<User>,

    @SerializedName("token")
    var token: String
)

@Parcelize
data class User(
    @SerializedName("name")
    @Expose
    var name: String = "",

    @SerializedName("phone")
    @Expose
    var phone: String = ""

) : Parcelable