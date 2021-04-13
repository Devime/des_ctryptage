package com.example.des_ctryptage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //rien de spécial que des boutons
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btsym: Button = findViewById<Button>(R.id.B1sym)
        btsym.setOnClickListener {
            val intent = Intent(this, Atbash::class.java)
            startActivity(intent)
        }
        val btplfr: Button = findViewById<Button>(R.id.B1PlayFair)
        btplfr.setOnClickListener {
            val intent = Intent(this, Playfair::class.java)
            startActivity(intent)
        }
        val btcesar: Button = findViewById<Button>(R.id.B1Cesar)
        btcesar.setOnClickListener {
            val intent = Intent(this, Cesar::class.java)
            startActivity(intent)

        }
        val bthill: Button = findViewById<Button>(R.id.B1Hill)
        bthill.setOnClickListener {
            val intent = Intent(this, Hill::class.java)
            startActivity(intent)

        }
        val bttrans: Button = findViewById<Button>(R.id.B1Transposition_rectangulaire)
        bttrans.setOnClickListener {
            val intent = Intent(this, Transpositionrect::class.java)
            startActivity(intent)

        }

        val btvigenere: Button = findViewById<Button>(R.id.B1Vigenere)
        btvigenere.setOnClickListener {
            val intent = Intent(this, Vigenere::class.java)
            startActivity(intent)

        }

        val btpolybe: Button = findViewById<Button>(R.id.B1Polybe)
        btpolybe.setOnClickListener {
            val intent = Intent(this, Polybe::class.java)
            startActivity(intent)

        }

        val btdelastelle: Button = findViewById<Button>(R.id.B1Delastelle)
        btdelastelle.setOnClickListener {
            val intent = Intent(this, Delastelle::class.java)
            startActivity(intent)

        }

    }
}
