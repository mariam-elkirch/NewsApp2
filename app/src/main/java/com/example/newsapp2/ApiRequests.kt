package com.example.newsapp2
import api.NewsJason;
import retrofit2.Call

import retrofit2.Response
import retrofit2.Retrofit

import retrofit2.http.GET
import retrofit2.http.Query
interface ApiRequests {
    @GET("top-headlines?country=us&apiKey=24a928ae8e564ad885294b3611404a5e")
     fun getNews():Call<NewsJason>
}