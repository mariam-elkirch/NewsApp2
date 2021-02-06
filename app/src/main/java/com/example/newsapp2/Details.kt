package com.example.newsapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class Details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val bundle: Bundle? = intent.extras
       // val string: String? = intent.getStringExtra("keyString")
        val myArray: ArrayList<String>? = intent.getStringArrayListExtra("keyString")
        Log.d("DetailsAct", myArray.toString())
        val w= myArray!!.get(0)
        val w1 = myArray!!.get(1)
        val w2=myArray.get(2)
        val image : ImageView =findViewById(R.id.image)
        val title : TextView =findViewById(R.id.dtitle)
        val content : TextView =findViewById(R.id.detail)
        title.setText(w1)
        content.setText(w2)
        Glide.with(this)
            .load(w)
            .into(image)

    }
}