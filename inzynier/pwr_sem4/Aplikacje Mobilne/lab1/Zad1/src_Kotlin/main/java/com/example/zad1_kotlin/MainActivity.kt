package com.example.zad1_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var points = 0
    private var n1 = 0
    private var n2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        points = 0
        generateNumbers()
    }

    private fun generateNumbers() {
        findViewById<TextView>(R.id.displayPoints).text = "Punkty: " + points
        n1 = Random.nextInt(10)
        n2 = Random.nextInt(10)
        while (n2 == n1) {
            n2 = Random.nextInt(10)
        }
        findViewById<Button>(R.id.buttonLeft).text = "" + n1
        findViewById<Button>(R.id.buttonRight).text = "" + n2
    }

    fun buttonLeftClicked(view: View) {
        if (n1 >= n2) {
            points++
            Toast.makeText(this, "Dobrze!", Toast.LENGTH_SHORT).show()
        }
        else {
            points--
            Toast.makeText(this, "Źle!", Toast.LENGTH_SHORT).show()
        }
        generateNumbers()
    }
    fun buttonRightClicked(view: View) {
        if (n2 >= n1) {
            points++
            Toast.makeText(this, "Dobrze!", Toast.LENGTH_SHORT).show()
        }
        else {
            points--
            Toast.makeText(this, "Źle!", Toast.LENGTH_SHORT).show()
        }
        generateNumbers()
    }
}