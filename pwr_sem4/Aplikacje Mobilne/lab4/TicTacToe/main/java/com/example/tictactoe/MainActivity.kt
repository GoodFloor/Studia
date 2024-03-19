package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val newGameActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result -> val data = result.data
        val winner = data!!.getStringExtra("Winner")
        if (winner == "T")
            Toast.makeText(this, "Remis!", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this, "Zwyciężył gracz '$winner'!", Toast.LENGTH_SHORT).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.txt_board_size).text = viewModel.getStringBoardSize()
        findViewById<Button>(R.id.btn_board_bigger).setOnClickListener {
            if (viewModel.getBoardSize() < 20) {
                viewModel.increaseBoardSize()
                findViewById<TextView>(R.id.txt_board_size).text = viewModel.getStringBoardSize()
            }
        }
        findViewById<Button>(R.id.btn_board_smaller).setOnClickListener {
            if (viewModel.getBoardSize() > 3) {
                viewModel.decreaseBoardSize()
                findViewById<TextView>(R.id.txt_board_size).text = viewModel.getStringBoardSize()
            }
        }
        findViewById<Button>(R.id.btn_start).setOnClickListener {
            var winSize = 3
            if (viewModel.getBoardSize() > 4)
                winSize = 4
            if (viewModel.getBoardSize() > 6)
                winSize = 5
            val gameIntent = Intent(this, GameActivity::class.java)
            gameIntent.putExtra("boardSize", viewModel.getBoardSize())
            gameIntent.putExtra("winSize", winSize)
            newGameActivity.launch(gameIntent)
        }
    }
}