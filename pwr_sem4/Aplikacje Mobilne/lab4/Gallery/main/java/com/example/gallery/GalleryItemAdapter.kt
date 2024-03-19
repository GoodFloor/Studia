package com.example.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import androidx.recyclerview.widget.RecyclerView

class GalleryItemAdapter(private val images: MutableList<GalleryItem>, private val parent: MainFragment): RecyclerView.Adapter<GalleryItemAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.imageView)
        var rating: RatingBar = view.findViewById(R.id.imageRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rating.rating = images[position].getRating()
        holder.imageView.setImageResource(images[position].getId())
        holder.imageView.setOnClickListener {
            parent.onClick(images[position], position)
        }
    }
}