package com.prateek.innews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.prateek.innews.adapters.NewsListAdapter
import com.prateek.innews.data.FetchNews
import com.prateek.innews.data.FetchNews.listNews
import com.prateek.innews.model.News
import com.prateek.innews.utils.BASE_URL
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    lateinit var requestQueue: RequestQueue
    lateinit var adapter: NewsListAdapter
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestQueue = Volley.newRequestQueue(this)
//        getAll(BASE_URL)




//        for(i in FetchNews.list) {
//            Log.d("News", "Title: ${i.title}\nDesc: ${i.description}\nURL: ${i.moreInfoUrl}\nImage: ${i.imageUrl}\n\n\n")
//        }


        getAll(BASE_URL)


        Log.d("WTF", "Layout set")

        FetchNews.getNews(this) {isDone ->

            if(isDone) {
                layoutManager = LinearLayoutManager(this)
                adapter = NewsListAdapter(this, FetchNews.listNews)

                newsListView.layoutManager = layoutManager
                newsListView.adapter = adapter



                println("List" + FetchNews.listNews)

                Log.d("WTF", "VIew set")

                adapter.notifyDataSetChanged()
            }

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
