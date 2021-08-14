package com.example.carrot.Response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class UserResponse(
    @SerializedName("User")
    var user: List<User>,

    @SerializedName("token")
    var token: String
)

@Parcelize
data class User(
    @SerializedName("id")
    @Expose
    var id: Int = 0,

    @SerializedName("PhoneNum")
    @Expose
    var PhoneNum: String = "",

) : Parcelable