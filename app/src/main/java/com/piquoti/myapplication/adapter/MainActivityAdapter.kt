package com.piquoti.myapplication.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.piquoti.myapplication.R
import com.piquoti.myapplication.app.model.ItunesItem
import com.piquoti.myapplication.app.util.RxBus
import com.piquoti.myapplication.ui.adapter.ItunesItemRowUI
import org.jetbrains.anko.AnkoContext

class MainActivityAdapter(var list: List<ItunesItem>,var context : Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  ItunesItemUIViewHolder(ItunesItemRowUI().createView(AnkoContext.create(parent.context, parent,false)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItunesItemUIViewHolder).loadData(list.get(position))
    }

    override fun getItemViewType(position: Int): Int {
        return 2000
    }

    override fun getItemCount(): Int {
        return this@MainActivityAdapter.list.count()
    }


    inner class ItunesItemUIViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var artistName: TextView = itemView.findViewById(ItunesItemRowUI.ARTISTNAME)
        private var trackImage: ImageView = itemView.findViewById(ItunesItemRowUI.TRACKIMAGE)
        private var price : TextView = itemView.findViewById(ItunesItemRowUI.PRICE)
        private var genre : TextView = itemView.findViewById(ItunesItemRowUI.GENRE)
        private var trackName: TextView = itemView.findViewById(ItunesItemRowUI.TRACKNAME)

        init {

        }

        fun loadData(item: ItunesItem){
            artistName.text = "Artist Name: " + item.artistName!!
            price.text = "Price: $" + item.trackPrice!! + " AUD"
            genre.text = "Genre: " + item.primaryGenreName!!
            trackName.text = "Track Name " + item.trackName!!

            itemView.setOnClickListener {
                RxBus.publish(item)
            }

            Glide
                .with(itemView)
                .load(item.artworkUrl60)
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder)
                .into(trackImage)

        }
    }

}