package com.example.saper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView.ScaleType
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.setPadding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val size: Int = 9
    private val bombsCount: Int = 10
    private var buttonsArray: MutableList<ImageButton> = ArrayList()
    private var board: Array<Int> = emptyArray()
    private var fieldIcons: Array<Int> = emptyArray()
    private var flagsCount: Int = 0
    private var revealedFields: Int = 0
    private var isFlagged: Array<Boolean> = emptyArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fieldIcons = arrayOf(R.drawable.field0, R.drawable.field1, R.drawable.field2, R.drawable.field3, R.drawable.field4, R.drawable.field5, R.drawable.field6, R.drawable.field7, R.drawable.field8, R.drawable.defused, R.drawable.flag, R.drawable.bomb, R.drawable.hidden_field)
        findViewById<Button>(R.id.restart).setOnClickListener {
            buttonsArray = ArrayList()
            board = emptyArray()
            generateBoard()
            drawBoard()
        }
        generateBoard()
        drawBoard()
    }
    private fun generateBoard() {
        flagsCount = 0
        revealedFields = 0
        board = Array(size * size) { 0 }
        isFlagged = Array(size * size) { false }
        var bombsSet = 0
        while (bombsSet != bombsCount) {
            val b = Random.nextInt(board.size)
            if (board[b] == -1)
                continue
            board[b] = -1
            bombsSet++
            var notFirstRow = true
            var notLastRow = true
            var notFirstColumn = true
            var notLastColumn = true
            if (b < size) //First row
                notFirstRow = false
            if (b >= board.size - size) //Last row
                notLastRow = false
            if (b % size == 0) //First column
                notFirstColumn = false
            if (b % size == size - 1) //Last column
                notLastColumn = false
            val idList: MutableList<Int> = ArrayList()
            if (notFirstRow)
                idList.add(b - size)
            if (notLastRow)
                idList.add(b + size)
            if (notFirstColumn)
                idList.add(b - 1)
            if (notLastColumn)
                idList.add(b + 1)
            if (notFirstRow && notFirstColumn)
                idList.add(b - size - 1)
            if (notFirstRow && notLastColumn)
                idList.add(b - size + 1)
            if (notLastRow && notLastColumn)
                idList.add(b + size + 1)
            if (notLastRow && notFirstColumn)
                idList.add(b + size - 1)
            idList.forEach {
                if (board[it] != -1)
                    board[it]++
            }
        }
    }
    private fun drawBoard() {
        findViewById<TextView>(R.id.appName).text = getString(R.string.app_name)
        val table = findViewById<TableLayout>(R.id.boardDisplay)
        table.removeAllViews()
        for (i in 0 until size) {
            val newRow = TableRow(this)
            for (j in 0 until size) {
                val newField = ImageButton(this)
                newField.setImageResource(R.drawable.hidden_field)
                newField.setPadding(0)
                newField.scaleType = ScaleType.CENTER_INSIDE
                newField.setOnClickListener { fieldClick(j, i) }
                newField.setOnLongClickListener { longClick(j, i) }
                newRow.addView(newField)
                buttonsArray.add(newField)
            }
            table.addView(newRow)
        }
    }
    private fun fieldClick(x: Int, y: Int) {
        if (x < 0 || y < 0 || x >= size || y >= size)
            return
        val b = size * y + x
        val currentButton = buttonsArray[b]
        if (!currentButton.isClickable)
            return
        if (isFlagged[b]) {
            isFlagged[b] = false
            flagsCount--
            buttonsArray[b].setImageResource(fieldIcons[fieldIcons.size - 1])
            return
        }
        currentButton.isClickable = false
        if (board[b] != -1) {
            revealedFields++
            currentButton.setImageResource(fieldIcons[board[b]])
            if (board[b] == 0) {
                fieldClick(x - 1, y - 1)
                fieldClick(x - 1, y)
                fieldClick(x - 1, y + 1)
                fieldClick(x, y -1)
                fieldClick(x, y + 1)
                fieldClick(x + 1, y - 1)
                fieldClick(x + 1, y)
                fieldClick(x + 1, y + 1)
            }
        }
        else {
            for (i in board.indices) {
                buttonsArray[i].isClickable = false
                if (board[i] == -1 && isFlagged[i]) {
                    buttonsArray[i].setImageResource(fieldIcons[fieldIcons.size - 4])
                }
                else if (board[i] == -1 && !isFlagged[i]) {
                    buttonsArray[i].setImageResource(fieldIcons[fieldIcons.size - 2])
                }
            }
            findViewById<TextView>(R.id.appName).text = getString(R.string.gameLost)
        }
        checkWin()
    }
    private fun longClick(x: Int, y: Int): Boolean {
        val b = size * y + x
        if (isFlagged[b] || !buttonsArray[b].isClickable)
            return true
        isFlagged[b] = true
        flagsCount++
        buttonsArray[b].setImageResource(fieldIcons[fieldIcons.size - 3])
        checkWin()
        return true
    }
    private fun checkWin() {
        if (flagsCount + revealedFields != size * size || flagsCount != bombsCount)
            return
        findViewById<TextView>(R.id.appName).text = getString(R.string.gameWon)
        for (i in board.indices) {
            buttonsArray[i].isClickable = false
            if (board[i] == -1) {
                buttonsArray[i].setImageResource(fieldIcons[fieldIcons.size - 4])
            }
        }
    }
}