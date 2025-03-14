package com.example.tictactoe

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.TicTacToeTheme

class GameActivity : ComponentActivity() {
    private var boardSize = 3
    private var winSize = 3
    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        boardSize = intent.getIntExtra("boardSize", 3)
        winSize = intent.getIntExtra("winSize", 3)
        viewModel.setupBoard(boardSize)
        setContent {
            TicTacToeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting(winSize)
                    DrawBoard(boardSize)
                }
            }
        }
    }
    private fun checkDrawConditions(): Boolean {
        var board = viewModel.getBoard()
        for (r in board)
            for (c in r)
                if (c == 0)
                    return false
        return true
    }
    private fun checkWinConditions(x: Int, y: Int): Boolean {
        var board = viewModel.getBoard()
        var longestLine: Int
        var currLine = 0
        var i = x
        var j = y
        // Pionowo
        while (j >= 0 && board[x][y] == board[i][j])
            j--
        j++
        while (j < boardSize && board[x][y] == board[i][j]) {
            currLine++
            j++
        }
        longestLine = currLine
        // Ukos lewo - prawo
        i = x
        j = y
        currLine = 0
        while (i >= 0 && j >= 0 && board[x][y] == board[i][j]) {
            i--
            j--
        }
        i++
        j++
        while (i < boardSize && j < boardSize && board[x][y] == board[i][j]) {
            currLine++
            i++
            j++
        }
        if (currLine > longestLine)
            longestLine = currLine
        // Poziomo
        i = x
        j = y
        currLine = 0
        while (i >= 0 && board[x][y] == board[i][j])
            i--
        i++
        while (i < boardSize && board[x][y] == board[i][j]) {
            currLine++
            i++
        }
        if (currLine > longestLine)
            longestLine = currLine
        // Ukos prawo - lewo
        i = x
        j = y
        currLine = 0
        while (i >= 0 && j < boardSize && board[x][y] == board[i][j]) {
            i--
            j++
        }
        i++
        j--
        while (i < boardSize && j >= 0 && board[x][y] == board[i][j]) {
            currLine++
            i++
            j--
        }
        if (currLine > longestLine)
            longestLine = currLine

        if (longestLine >= winSize)
            return true
        return false
    }
    private fun makeMove(x: Int, y: Int) {
        if (viewModel.getBoard(x, y) != 0)
            return
        if (viewModel.isPlayerOneTurn())
            viewModel.boardSet(x, y, 1)
        else
            viewModel.boardSet(x, y, 2)
        if(checkWinConditions(x, y)) {
            val thisIntent = Intent()
//            var arrStr = ""
//            for (i in 0 until boardSize) {
//                for (j in 0 until boardSize) {
//                    arrStr += board[j][i].toString()
//                }
//                arrStr += "\n"
//            }
            if (viewModel.isPlayerOneTurn())
                thisIntent.putExtra("Winner", "O")
            else
                thisIntent.putExtra("Winner", "X")
            setResult(Activity.RESULT_OK, thisIntent)
            finish()
        }
        else if (checkDrawConditions()) {
            val thisIntent = Intent()
            thisIntent.putExtra("Winner", "T")
            setResult(Activity.RESULT_OK, thisIntent)
            finish()
        }
        viewModel.swapPlayers()
    }
    @Composable
    fun Greeting(requiredLength: Int, modifier: Modifier = Modifier) {
        Text(
            text = "Ułóż $requiredLength symbole/i pod rząd!",
            modifier = modifier
        )
    }

//    @Composable
//    fun BoardField(x: Int, y: Int) {
//        var text by remember { mutableStateOf(" ") }
//        var btnState by remember { mutableStateOf(true) }
//        val screenWidth = LocalConfiguration.current.screenWidthDp
//        Button(
//            onClick = {
//                if (isPlayerOneTurn)
//                    text = "O"
//                else
//                    text = "X"
//                makeMove(x, y)
//                btnState = false
//                      },
//            shape = RectangleShape,
//            border = BorderStroke(1.dp, Color.Black),
//            colors = ButtonDefaults.buttonColors(),
//            modifier = Modifier
//                .width((screenWidth / boardSize).dp)
//                .height((screenWidth / boardSize).dp),
//            enabled = btnState,
//            contentPadding = PaddingValues(0.dp)
//        ) {
//            Text(text = text)
//        }
//    }

    @Composable
    fun DrawBoard(boardSize: Int) {
        var currentPlayerReminder by remember { mutableStateOf("Zaczyna 'O'") }
        if (viewModel.isPlayerOneTurn())
            currentPlayerReminder = "Teraz 'O'"
        else
            currentPlayerReminder = "Teraz 'X'"
        var screenWidth = LocalConfiguration.current.screenWidthDp
        if (LocalConfiguration.current.screenHeightDp < screenWidth)
            screenWidth = LocalConfiguration.current.screenHeightDp - 64
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = currentPlayerReminder, fontSize = 32.sp)
            for (i in 0 until boardSize) {
                Row {
                    for (j in 0 until boardSize) {
                        var text by remember { mutableStateOf(" ") }
                        var btnState by remember { mutableStateOf(true) }
                        if (viewModel.getBoard(j, i) == 1) {
                            text = "O"
                            btnState = false
                        }
                        else if (viewModel.getBoard(j, i) == 2) {
                            text = "X"
                            btnState = false
                        }
                        Button(
                            onClick = {
                                if (viewModel.isPlayerOneTurn()) {
                                    text = "O"
                                    currentPlayerReminder = "Teraz 'X'"
                                }
                                else {
                                    text = "X"
                                    currentPlayerReminder = "Teraz 'O'"
                                }
                                makeMove(j, i)
                                btnState = false
                            },
                            shape = RectangleShape,
                            border = BorderStroke(1.dp, Color.Black),
                            colors = ButtonDefaults.buttonColors(),
                            modifier = Modifier
                                .width((screenWidth / boardSize).dp)
                                .height((screenWidth / boardSize).dp),
                            enabled = btnState,
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                text = text,
                                fontSize = (screenWidth / boardSize / 2).sp
                            )
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        TicTacToeTheme {
            Greeting(3)
        }
    }
}



