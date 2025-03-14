package com.example.zad2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var pointsCPU = 0
    private var pointsPlayer = 0
    private var moveCPU = 0     //0 = kamień, 1 = papier, 2 = nożyce
    private var movePlayer = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pointsCPU = 0
        pointsPlayer = 0
        findViewById<TextView>(R.id.choiceCPU).text = ""
        findViewById<TextView>(R.id.choicePlayer).text = ""
        findViewById<TextView>(R.id.pointsCPU).text = "" + pointsCPU
        findViewById<TextView>(R.id.pointsPlayer).text = "" + pointsPlayer
    }
    private fun roll() {
        moveCPU = Random.nextInt(3)
        if (moveCPU == 0) {
            findViewById<TextView>(R.id.choiceCPU).text = "Kamień"
        }
        else if (moveCPU == 1) {
            findViewById<TextView>(R.id.choiceCPU).text = "Papier"
        }
        else {
            findViewById<TextView>(R.id.choiceCPU).text = "Nożyce"
        }
    }
    private fun fight() {
        if (movePlayer == 0) {
            findViewById<TextView>(R.id.choicePlayer).text = "Kamień"
        }
        else if (movePlayer == 1) {
            findViewById<TextView>(R.id.choicePlayer).text = "Papier"
        }
        else {
            findViewById<TextView>(R.id.choicePlayer).text = "Nożyce"
        }
        if (moveCPU == movePlayer) {
            Toast.makeText(this, "Remis!", Toast.LENGTH_SHORT).show()
        }
        else if ((moveCPU == 0 && movePlayer == 1) || (moveCPU == 1 && movePlayer == 2) || (moveCPU == 2 && movePlayer == 0)) {
            pointsPlayer++
            findViewById<TextView>(R.id.pointsPlayer).text = "" + pointsPlayer
            Toast.makeText(this, "Wygrana!", Toast.LENGTH_SHORT).show()
        }
        else {
            pointsCPU++
            findViewById<TextView>(R.id.pointsCPU).text = "" + pointsCPU
            Toast.makeText(this, "Przegrana!", Toast.LENGTH_SHORT).show()
        }
    }
    fun rock(view: View) {
        roll()
        movePlayer = 0
        fight()
    }
    fun paper(view: View) {
        roll()
        movePlayer = 1
        fight()
    }
    fun scissors(view: View) {
        roll()
        movePlayer = 2
        fight()
    }
}