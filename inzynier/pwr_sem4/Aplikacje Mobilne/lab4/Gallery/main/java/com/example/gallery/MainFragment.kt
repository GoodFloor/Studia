package com.example.gallery

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

class MainFragment : Fragment() {
    private var imageList: MutableList<GalleryItem> = ArrayList()
    private val imageDetailsActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result -> val data = result.data
        if (data != null) {
            val changedImage = data!!.getIntExtra("position", 0)
            val newRating = data!!.getFloatExtra("newRating", 2.5f)
            imageList[changedImage].setRating(newRating)
            imageList.sortByDescending { it.getRating() }
            redrawGallery()
        }
    }
    private lateinit var recyclerView: RecyclerView
    private lateinit var snapHelper: SnapHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflat = inflater.inflate(R.layout.fragment_main, container, false)
        recyclerView = inflat.findViewById(R.id.galleryContents)
        snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        if (savedInstanceState == null)
            setupData()
        else {
            val numberOfImages = savedInstanceState.getInt("numberOfImages", 5)
            for (i in 0 until numberOfImages) {
                val id = savedInstanceState.getInt("id$i", R.drawable.item_mona_lisa)
                val desc = savedInstanceState.getString("description$i", "")
                val rat = savedInstanceState.getFloat("rating$i", 0.5f)
                imageList.add(GalleryItem(id, desc, rat))
            }
        }
        redrawGallery()
        return inflat
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("numberOfImages", imageList.size)
        for (i in imageList.indices) {
            outState.putInt("id$i", imageList[i].getId())
            outState.putString("description$i", imageList[i].getDescription())
            outState.putFloat("rating$i", imageList[i].getRating())
        }
    }

    private fun redrawGallery() {
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = GalleryItemAdapter(imageList, this)
    }

    private fun setupData() {
        if (imageList.size == 0)
            for (i in 0 until GalleryItem.getSize())
                imageList.add(GalleryItem(i))
    }
    fun onClick(galleryItem: GalleryItem, position: Int) {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val imageDetails = Intent(activity, ImageDetailsActivity::class.java)
            imageDetails.putExtra("id", galleryItem.getId())
            imageDetails.putExtra("rating", galleryItem.getRating())
            imageDetails.putExtra("description", galleryItem.getDescription())
            imageDetails.putExtra("position", position)
            imageDetailsActivity.launch(imageDetails)
        }
        else {
            val fragment = parentFragmentManager.findFragmentById(R.id.fragment_image_details) as GalleryItemFragment
            fragment.display(galleryItem.getId(), galleryItem.getRating(), galleryItem.getDescription(), position, this)
        }
    }
    fun editItem(position: Int, newRating: Float) {
        imageList[position].setRating(newRating)
        imageList.sortByDescending { it.getRating() }
        redrawGallery()
    }
}