package com.example.uas.API

import com.example.uas.data.DataItem
import retrofit2.Call
import retrofit2.http.GET

interface APIGet {
    @GET("retrieve.php")
    fun getUserData(): Call<List<DataItem>>
}