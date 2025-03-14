package com.example.tictactoe

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var boardSize = 3

    fun getBoardSize(): Int {
        return boardSize
    }
    fun getStringBoardSize(): String {
        return boardSize.toString()
    }
    fun increaseBoardSize() {
        boardSize++
    }
    fun decreaseBoardSize() {
        boardSize--
    }
}