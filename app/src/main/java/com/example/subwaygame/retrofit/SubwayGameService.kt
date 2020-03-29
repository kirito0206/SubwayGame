package com.example.subwaygame.retrofit

import com.example.subwaygame.data.Questions
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SubwayGameService {
    @GET("wenda/index")
    fun getQuestion(@Query("key") key : String) : Call<Questions>
}