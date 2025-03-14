package com.example.tictactoe

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel(){
    private var board = emptyArray<Array<Int>>()
    private var playerOneTurn = true

    fun setupBoard(boardSize: Int) {
        if (boardSize != board.size)
            board = Array(boardSize) { Array(boardSize) { 0 } }
    }
    fun getBoard(): Array<Array<Int>> {
        return board
    }
    fun getBoard(x: Int, y: Int): Int {
        return board[x][y]
    }
    fun boardSet(x: Int, y: Int, value: Int) {
        board[x][y] = value
    }
    fun isPlayerOneTurn(): Boolean {
        return playerOneTurn
    }
    fun swapPlayers() {
        playerOneTurn = !playerOneTurn
    }
}