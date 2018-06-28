package com.prateek.innews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.prateek.innews.data.FetchNews
import com.prateek.innews.utils.BASE_URL
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        requestQueue = Volley.newRequestQueue(this)
//        getAll(BASE_URL)

        FetchNews.getNews(this)

        for(i in FetchNews.list) {
            Log.d("News", "Title: ${i.title}\nDesc: ${i.description}\nURL: ${i.moreInfoUrl}\nImage: ${i.imageUrl}\n\n\n")
        }

    }




    //temporary function
    fun getAll(url: String) {
        var req = StringRequest(Request.Method.GET, url, Response.Listener { response ->
            try {
                Log.d("RES: ", response.toString())
            } catch (e: JSONException) {
                Log.e("CATCH: ", e.localizedMessage)
            }
        }, Response.ErrorListener { error ->
            Log.e("ErrorListener", error.localizedMessage)

        })

        requestQueue.add(req)
    }
}
