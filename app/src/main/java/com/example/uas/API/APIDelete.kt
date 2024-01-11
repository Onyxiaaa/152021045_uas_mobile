package com.example.uas.API

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiDelete{
    @FormUrlEncoded
    @POST("delete.php")
    fun deleteUser(@Field("id") id: Int): Call<Void>
}
