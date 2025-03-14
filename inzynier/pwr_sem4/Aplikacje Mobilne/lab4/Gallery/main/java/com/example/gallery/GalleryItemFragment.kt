package com.example.gallery

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

class GalleryItemFragment : Fragment() {

    lateinit var txt: TextView
    lateinit var rat: RatingBar
    lateinit var img: ImageView
    lateinit var btn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflat = inflater.inflate(R.layout.fragment_gallery_item, container, false)
        txt = inflat.findViewById<TextView>(R.id.detailsDescription)
        rat = inflat.findViewById<RatingBar>(R.id.detailsRating)
        img = inflat.findViewById<ImageView>(R.id.detailsImage)
        btn =  inflat.findViewById<Button>(R.id.button)
        btn.setOnClickListener {
            onClick()
        }
        return inflat
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity?.intent != null) {
            txt.text = requireActivity().intent.getStringExtra("description")
            rat.rating = requireActivity().intent.getFloatExtra("rating", 1f)
            img.setImageResource(requireActivity().intent.getIntExtra("id", R.drawable.item_mona_lisa))
        }
    }

    private fun onClick() {
        val thisIntent = Intent()
        thisIntent.putExtra("position", requireActivity().intent.getIntExtra("position", 0))
        thisIntent.putExtra("newRating", rat.rating)
        requireActivity().setResult(Activity.RESULT_OK, thisIntent)
        requireActivity().finish()
    }
    private fun altOnClick(position: Int, mainFragment: MainFragment) {
        mainFragment.editItem(position, rat.rating)
    }

    fun display(
        id: Int,
        rating: Float,
        description: String,
        position: Int,
        mainFragment: MainFragment
    ) {
        txt.text = description
        rat.rating = rating
        img.setImageResource(id)
        btn.setOnClickListener {
            altOnClick(position, mainFragment)
        }
    }
}