package com.example.newsapp2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api.Article
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

const val  baseUrl="https://newsapi.org/v2/"
class MainActivity : AppCompatActivity() {
    //var layoutManager: RecyclerView.Layou    var recyclerView: RecyclerView? = nulltManager? = null

    //
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: NewsRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

       recyclerView.layoutManager=linearLayoutManager
        adapter = NewsRecyclerAdapter(null)
        recyclerView.adapter = adapter
        getCurrentData()
        layout_generate_news.setOnClickListener {
            getCurrentData()
        }
    }

    private fun getCurrentData() {



        val api = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getNews().awaitResponse()
                Log.d("responseeee", response.toString())
                if (response.isSuccessful) {

                    val data = response.body()!!
                   // Log.d("mydataaaaaaaaa", data.toString())
                    val newsList: List<Article> = data.articles
                  //  Log.d("mydataaaaaaaaa", newsList.toString())
                    withContext(Dispatchers.Main) {
                       adapter.changeData(newsList)

                       // adapter?.changeData(newsList)
                        Log.d("data", newsList.toString())
                        Log.d("adapterdata", adapter.toString())

                    }

                }
                Log.d("errrrr", "errrrrrrrrrr")
            } catch (e: Exception) {
                withContext(Dispatchers.Main){
                    Toast.makeText(
                        applicationContext,
                        "Seems like something went wrong...",
                        Toast.LENGTH_SHORT


                    ).show()
                }
            }
        }


    }
}