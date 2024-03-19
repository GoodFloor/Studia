package com.example.calendar

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView

class CreatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creating)
        findViewById<TextView>(R.id.newActivityDate).text = intent.getStringExtra("date")
        findViewById<Button>(R.id.btn_confirm_new_activity).setOnClickListener {
            val myIntent = Intent()
            myIntent.putExtra("time", intent.getStringExtra("time"))
            myIntent.putExtra("name", findViewById<TextView>(R.id.newActivityName).text.toString())
            myIntent.putExtra("importance", findViewById<SeekBar>(R.id.newActivityImportance).progress.toString())
            setResult(Activity.RESULT_OK, myIntent)
            finish()
        }
    }

}