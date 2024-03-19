package com.example.circularprogressbardemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val circularProgressBar = findViewById<CircularProgressBar>(R.id.circularProgressBar)
        circularProgressBar.setProgress(0f)
        findViewById<Button>(R.id.button).setOnClickListener {
            Thread {
                for (i in 1..1000) {
                    circularProgressBar.setProgress(i / 10f)
                    Log.d("onClick", "Loop: $i")
                    Thread.sleep(50)
                }
            }.start()
        }
    }
}