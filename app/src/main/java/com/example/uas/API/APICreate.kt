package com.example.uas.API

import com.example.uas.data.DataItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APICreate {
    @POST("create.php")
    fun addUser(@Body user: DataItem): Call<DataItem>
}