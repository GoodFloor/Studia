package com.example.zad1_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int points;
    private int n1;
    private int n2;
    private Random rand;
    private TextView pointsDisplay;
    private Button buttonLeft;
    private Button buttonRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        points = 0;
        rand = new Random();
        pointsDisplay = findViewById(R.id.displayPoints);
        buttonLeft = findViewById(R.id.buttonLeft);
        buttonRight = findViewById(R.id.buttonRight);
        generateNumbers();
    }

    private void generateNumbers() {
        pointsDisplay.setText("Punkty: " + points);
        n1 = rand.nextInt(10);
        n2 = rand.nextInt(10);
        while (n1 == n2) {
            n2 = rand.nextInt(10);
        }
        buttonLeft.setText("" + n1);
        buttonRight.setText("" + n2);
    }

    public void buttonLeftClicked(View view) {
        if (n1 > n2) {
            points++;
            Toast.makeText(this, "Dobrze!", Toast.LENGTH_SHORT).show();
        }
        else {
            points--;
            Toast.makeText(this, "Źle!", Toast.LENGTH_SHORT).show();
        }
        generateNumbers();
    }

    public void buttonRightClicked(View view) {
        if (n1 < n2) {
            points++;
            Toast.makeText(this, "Dobrze!", Toast.LENGTH_SHORT).show();
        }
        else {
            points--;
            Toast.makeText(this, "Źle!", Toast.LENGTH_SHORT).show();
        }
        generateNumbers();
    }
}