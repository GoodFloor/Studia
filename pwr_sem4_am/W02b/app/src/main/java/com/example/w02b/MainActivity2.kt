package com.example.w02b

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val str = intent.getStringExtra("tag");
        findViewById<TextView>(R.id.textView).text = str;
    }

    fun click(view: View) {
        val myIntenet = Intent()
        myIntenet.putExtra("tag", "Hello from activity2")
        setResult(Activity.RESULT_OK, myIntenet)
        finish()
    }
}