package com.example.carrot.Response

import com.google.gson.annotations.SerializedName

data class ImageUploadResponse(
    @SerializedName("upload")
    var uploadImage: ArrayList<UploadImage>
)

data class UploadImage(
    @SerializedName("filename")
    var fileName: String
)