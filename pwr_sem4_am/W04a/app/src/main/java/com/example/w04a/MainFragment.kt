package com.example.w04a

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        view?.findViewById<Button>(R.id.button1)!!.setOnClickListener { onClick(it) }
        view?.findViewById<Button>(R.id.button2)!!.setOnClickListener { onClick(it) }
        view?.findViewById<Button>(R.id.button3)!!.setOnClickListener { onClick(it) }
        return view
    }

    fun onClick(view: View) {
        var s = when ((view as Button).id) {
            R.id.button1 -> "Button 1 clicked"
            R.id.button2 -> "Button 2 clicked"
            else -> "Button 3 clicked"
        }
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            var myIntent = Intent(activity, MainActivity2::class.java)
            myIntent.putExtra("data", s)
            startActivity(myIntent)
        }
        else {
            var frag = parentFragmentManager.findFragmentById(R.id.fragment2) as MainFragment2
            frag.display(s)
        }
    }
}