package com.example.kostin_up

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imageButton : ImageButton = findViewById(R.id.imageView)

        imageButton.setOnClickListener{

            var intent=Intent(this, MainActivity2::class.java )
            startActivity(intent)
        }
    }
}