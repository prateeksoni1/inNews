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

    var listNews = ArrayList<News>()

    fun getNews(context: Context, complete: (Boolean) -> Unit) {
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

                    if(!(title == "null") && !(desc == "null") && !(img == "null") && !(moreUrl == "null")) {
                        Log.d("OBJ", "${news.title}")
                        listNews.add(news)
                      //  Log.d("LIST", listNews[i].title)
                        complete(true)
                    }

                }

            } catch (e: JSONException) {
                Log.d("Catch", e.localizedMessage)
                complete(false)
            }

        }, Response.ErrorListener {
            it.printStackTrace()
            complete(false)
        })

        Volley.newRequestQueue(context).add(objReq)


    }
}