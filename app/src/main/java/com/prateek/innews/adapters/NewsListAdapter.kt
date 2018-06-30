package com.prateek.innews.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prateek.innews.R
import com.prateek.innews.model.News
import kotlinx.android.synthetic.main.list_item.view.*
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import com.prateek.innews.WebActivity
import com.squareup.picasso.Picasso
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class NewsListAdapter(val context: Context, val newsList: ArrayList<News>) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        Log.d("CREATE", "View created")
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return newsList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindView(newsList[position])

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.title
        val desc = itemView.description
        val image = itemView.image
        val more = itemView.readMoreBtn

        fun bindView(news: News) {
            title.text = news.title
            desc.text = news.description
            Picasso.get().load(news.imageUrl).into(image)
            Log.d("VIEW", "Done binding")

            more.setOnClickListener {
                var intent = Intent(context, WebActivity::class.java)
                intent.putExtra("LINK", news.moreInfoUrl)
                context.startActivity(intent)
            }

        }


    }
}