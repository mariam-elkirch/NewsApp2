package com.example.newsapp2
import api.NewsJason;
import retrofit2.Call

import retrofit2.Response
import retrofit2.Retrofit

import retrofit2.http.GET
import retrofit2.http.Query
interface ApiRequests {
    @GET("top-headlines?country=us&apiKey=2b73148720ee4a6c98910ea36c3b11d3")
     fun getNews():Call<NewsJason>
}