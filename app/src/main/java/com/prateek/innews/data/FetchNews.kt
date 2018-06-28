package com.prateek.innews.data

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.prateek.innews.model.News
import com.prateek.innews.utils.BASE_URL
import org.json.JSONException
import org.json.JSONObject

object FetchNews {

    var list = ArrayList<News>()

    fun getNews(context: Context) {
        var objReq = JsonObjectRequest(Request.Method.GET, BASE_URL, null, Response.Listener { response ->
            try {
                var jsonArray = response.getJSONArray("articles")
                for (i in 0 until jsonArray.length()) {

                    val obj = jsonArray.getJSONObject(i)
                    val title = obj.get("title").toString()
                    val desc = obj.get("description").toString()
                    val img = obj.get("urlToImage").toString()
                    val moreUrl = obj.get("url").toString()

                    val news = News(title, desc, img, moreUrl)
              //      Log.d("OBJ", "${news.title}")
                    list.add(news)
//                    Log.d("LIST", list[i].title)

                }

            } catch (e: JSONException) {
                Log.d("Catch", e.localizedMessage)
            }

        }, Response.ErrorListener {
            it.printStackTrace()
        })

        Volley.newRequestQueue(context).add(objReq)


    }
}