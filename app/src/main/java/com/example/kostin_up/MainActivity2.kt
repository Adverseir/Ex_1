package com.example.kostin_up

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var Button : Button = findViewById(R.id.button)
        Button.setOnClickListener{

            var intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}