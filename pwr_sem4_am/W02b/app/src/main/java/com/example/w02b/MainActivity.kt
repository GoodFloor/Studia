package com.example.w02b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun click(view: View) {
        val myintent = Intent(this, MainActivity2::class.java)
        myintent.putExtra("tag", "Cześć z tagu")
        //startActivity(myintent)
        resultLauncher.launch(myintent)
    }
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result -> val data = result.data
        Toast.makeText(this, data?.getStringExtra("tag"), Toast.LENGTH_SHORT).show()
    }
}