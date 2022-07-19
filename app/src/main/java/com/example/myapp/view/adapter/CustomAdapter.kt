package com.example.myapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.data.Result
import com.squareup.picasso.Picasso

class CustomAdapter(
    private val mList: List<Result?>,
    val mItemClickListener: ItemClickListener
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private val path = "https://image.tmdb.org/t/p/w500"

    interface ItemClickListener {
        fun onItemClick(id: Int)
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val image: ImageView = item.findViewById(R.id.imageView)

        init {
            item.setOnClickListener {
                mList?.get(position)?.id?.let{it -> mItemClickListener.onItemClick(it)}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(path + mList?.get(position)?.poster_path).into(holder.image)
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }
}