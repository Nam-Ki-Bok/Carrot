package com.example.carrot.Service

import com.example.carrot.Response.ImageUploadResponse
import com.example.carrot.Response.ReadSaleResponse
import com.example.carrot.Response.WriteSaleResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface SaleService {
    @FormUrlEncoded
    @POST("")
    fun writeSale(
        @Header("authorization") token: String,
        @Field("title") title: String,
        @Field("content") content: String,
        @Field("price") price: String,
        @Field("priceProposal") priceProposal: Int,
        @Field("writer") writer: Int
    ): Call<WriteSaleResponse>

    @Multipart
    @POST("")
    fun uploadImage(
        @Header("authorization") token: String,
        @Part image: List<MultipartBody.Part>
    ): Call<ImageUploadResponse>

    @FormUrlEncoded
    @POST("")
    fun writeImage(
        @Header("authorization") token: String,
        @Field("saleId") saleId: Int,
        @Field("count") count: Int,
        @Field("images") images: ArrayList<String>
    ): Call<Void>


}