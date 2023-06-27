package com.example.w02a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("State", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("State", "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("State", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("State", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("State", "onDestroy")
    }

    override fun onResume() {
        super.onResume()
        Log.i("State", "onResume")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("State", "onSave")
        outState.putCharSequence("key", findViewById<TextView>(R.id.textView).text)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("State", "onRestore")
        findViewById<TextView>(R.id.textView).text = savedInstanceState.getCharSequence("key")
    }

//    fun click(view: View) {
//        findViewById<TextView>(R.id.textView).text = findViewById<EditText>(R.id.editField).text
//    }

}