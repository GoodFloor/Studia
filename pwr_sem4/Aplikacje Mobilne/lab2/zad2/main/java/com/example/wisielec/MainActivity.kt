package com.example.wisielec

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var passArray: Array<String> = emptyArray()
    private var errorImgArray: Array<Int> = emptyArray()
    private var currentPass: String = ""
    private var mistakes: Int = 0
    private var guessedArray = BooleanArray(1)
    private var buttonArray: Array<Int> = emptyArray()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        passArray = resources.getStringArray(R.array.passwords)
        errorImgArray = arrayOf(R.drawable.wisielec0, R.drawable.wisielec1, R.drawable.wisielec2, R.drawable.wisielec3, R.drawable.wisielec4, R.drawable.wisielec5, R.drawable.wisielec6, R.drawable.wisielec7, R.drawable.wisielec8, R.drawable.wisielec9)
        buttonArray = arrayOf(R.id.buttonA, R.id.buttonB, R.id.buttonC, R.id.buttonD, R.id.buttonE, R.id.buttonF, R.id.buttonG, R.id.buttonH, R.id.buttonI, R.id.buttonJ, R.id.buttonK, R.id.buttonL, R.id.buttonM, R.id.buttonN, R.id.buttonO, R.id.buttonP, R.id.buttonR, R.id.buttonS, R.id.buttonT, R.id.buttonU, R.id.buttonW, R.id.buttonY, R.id.buttonZ)
        for (i in buttonArray.indices) {
            val currentButton: Button = findViewById(buttonArray[i])
            currentButton.setOnClickListener {
                val c = currentButton.text[0]
                var correct = 0
                for (j in currentPass.indices) {
                    if (c == currentPass[j]) {
                        guessedArray[j] = true
                        correct++
                    }
                }
                if (correct == 0) {
                    val imgRsc = errorImgArray[mistakes]
                    findViewById<ImageView>(R.id.errorCount).setImageResource(imgRsc)
                    mistakes++
                    if (mistakes == errorImgArray.size) {
                        for (j in buttonArray.indices) {
                            findViewById<Button>(buttonArray[j]).isEnabled = false
                        }
                        Toast.makeText(this, "Przegrana! Hasło to: " + currentPass, Toast.LENGTH_SHORT).show()
                    }
                }
                currentButton.isEnabled = false
                printPass()
                if (findViewById<TextView>(R.id.password).text == currentPass) {
                    for (j in buttonArray.indices) {
                        findViewById<Button>(buttonArray[j]).isEnabled = false
                    }
                    findViewById<ImageView>(R.id.errorCount).setImageResource(R.drawable.win)
                }
            }
        }
        findViewById<Button>(R.id.buttonRestart).setOnClickListener { newPass() }
        newPass()
    }

    private fun newPass() {
        val r = Random.nextInt(passArray.size)
        currentPass = passArray[r].uppercase()
        guessedArray = BooleanArray(currentPass.length)
        mistakes = 0
        findViewById<ImageView>(R.id.errorCount).setImageResource(android.R.color.transparent)
        for (i in guessedArray.indices) {
            guessedArray[i] = false
        }
        for (i in buttonArray.indices) {
            findViewById<Button>(buttonArray[i]).isEnabled = true
        }
        printPass()
    }
    private fun printPass(){
        var printable = ""
        for (i in currentPass.indices) {
            if (guessedArray[i]) {
                printable += currentPass[i]
            }
            else {
                printable += "?"
            }
        }
        findViewById<TextView>(R.id.password).text = printable
    }
}
