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
            var intent = Intent(this, Atbash::class.java)
            startActivity(intent)
        }
        var btplfr: Button = findViewById<Button>(R.id.B1PlayFair)
        btplfr.setOnClickListener {
            var intent = Intent(this, Playfair::class.java)
            startActivity(intent)
        }
        var btcesar: Button = findViewById<Button>(R.id.B1Cesar)
        btcesar.setOnClickListener {
            var intent = Intent(this, Cesar::class.java)
            startActivity(intent)

        }
        var bthill: Button = findViewById<Button>(R.id.B1Hill)
        bthill.setOnClickListener {
            var intent = Intent(this, Hill::class.java)
            startActivity(intent)

        }

        var btvigenere: Button = findViewById<Button>(R.id.B1Vigenere)
        btvigenere.setOnClickListener {
            var intent = Intent(this, Vigenere::class.java)
            startActivity(intent)

        }


    }
}
