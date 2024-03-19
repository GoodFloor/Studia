package com.example.zad2_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int pointsCPU;
    private int pointsPlayer;
    private int moveCPU;
    private int movePlayer;
    private TextView displayCPUMove;
    private TextView displayPlayerMove;
    private TextView displayCPUPoints;
    private TextView displayPlayerPoints;
    private Random rand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pointsCPU = 0;
        pointsPlayer = 0;
        displayCPUMove = findViewById(R.id.choiceCPU);
        displayCPUMove.setText("");
        displayPlayerMove = findViewById(R.id.choicePlayer);
        displayPlayerMove.setText("");
        displayCPUPoints = findViewById(R.id.pointsCPU);
        displayCPUPoints.setText("" + pointsCPU);
        displayPlayerPoints = findViewById(R.id.pointsPlayer);
        displayPlayerPoints.setText("" + pointsPlayer);
        rand = new Random();
    }
    private void roll() {
        moveCPU = rand.nextInt(3);
        switch (moveCPU) {
            case 0:
                displayCPUMove.setText("Kamień");
                break;
            case 1:
                displayCPUMove.setText("Papier");
                break;
            case 2:
                displayCPUMove.setText("Nożyce");
                break;
        }

    }
    private void fight() {
        switch (movePlayer) {
            case 0:
                displayPlayerMove.setText("Kamień");
                break;
            case 1:
                displayPlayerMove.setText("Papier");
                break;
            case 2:
                displayPlayerMove.setText("Nożyce");
                break;
        }
        if (moveCPU == movePlayer) {
            Toast.makeText(this, "Remis!", Toast.LENGTH_SHORT).show();
        } else if ((moveCPU == 0 && movePlayer == 1) || (moveCPU == 1 && movePlayer == 2) || (moveCPU == 2 && movePlayer == 0)) {
            pointsPlayer++;
            displayPlayerPoints.setText("" + pointsPlayer);
            Toast.makeText(this, "Wygrana!", Toast.LENGTH_SHORT).show();
        } else {
            pointsCPU++;
            displayCPUPoints.setText("" + pointsCPU);
            Toast.makeText(this, "Przegrana!", Toast.LENGTH_SHORT).show();
        }
    }
    public void rock(View view) {
        roll();
        movePlayer = 0;
        fight();
    }
    public void paper(View view) {
        roll();
        movePlayer = 1;
        fight();
    }
    public void scissors(View view) {
        roll();
        movePlayer = 2;
        fight();
    }
}
