package com.example.uas

import com.example.uas.API.APICreate
import com.example.uas.API.APIGet
import com.example.uas.API.ApiDelete
import com.example.uas.API.ApiUpdate
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {
    private const val BASE_URL = "http://192.168.56.1/uas_mobile/"

    private val gson = GsonBuilder().setLenient().create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val apiGet: APIGet = retrofit.create(APIGet::class.java)
    val apiCreate: APICreate = retrofit.create(APICreate::class.java)
    val apiUpdate : ApiUpdate = retrofit.create(ApiUpdate::class.java)
    val apiDelete : ApiDelete = retrofit.create(ApiDelete::class.java)
}