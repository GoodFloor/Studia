package com.example.w05a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val linear = findViewById<LinearLayout>(R.id.linearLayout)
        var imageView = ImageView(this)
        Picasso.get()
            .load("https://i.imgur.com/DvpvklR.png")
            .resize(1000, 1000)
            .into(imageView);
        linear.addView(imageView)
        for (i in 1..10) {
            var tv = TextView(this)
            tv.text = "Hello $i"
            tv.textSize = 50f
            tv.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, //WIDTH
                ViewGroup.LayoutParams.WRAP_CONTENT  //HEIGHT
            )
            linear.addView(tv)
            var view = layoutInflater.inflate(R.layout.item, null)
            var txt = view.findViewById<TextView>(R.id.textView)
            txt.text = "Hello"
            var btn = view.findViewById<Button>(R.id.button)
            btn.setOnClickListener {
                Toast.makeText(this, "Button $i", Toast.LENGTH_SHORT).show()
            }
            btn.text = "Button $i"
            linear.addView(view)
        }
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Opinia")
        builder.setMessage("Czy podoba ci się aplikacja?")
        builder.setPositiveButton("Tak") { _, _->
            Toast.makeText(this, "Super", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Nie :(") { _, _->
            Toast.makeText(this, "Szkoda :(", Toast.LENGTH_SHORT).show()
        }
        val dialog = builder.create()
        dialog.show()
//        var params = LinearLayout.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT, //WIDTH
//            ViewGroup.LayoutParams.MATCH_PARENT  //HEIGHT
//        )
//        params.weight = 1F;
//        params.gravity = Gravity.CENTER
//        linear.layoutParams = params
    }
}