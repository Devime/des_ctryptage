package com.example.des_ctryptage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btsym: Button = findViewById<Button>(R.id.B1sym)
        btsym.setOnClickListener {
            var intent=Intent(this, atbash::class.java)
            startActivity(intent)
        }
        var btplfr: Button = findViewById<Button>(R.id.B1PlayFair)
        btplfr.setOnClickListener {
            var intent=Intent(this, Playfair::class.java)
            startActivity(intent)
        }

    }
}