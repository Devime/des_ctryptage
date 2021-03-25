package com.example.des_ctryptage.com.example.des_ctryptage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.des_ctryptage.R

class Vigenere : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vigenere)
        var textentre: EditText = findViewById(R.id.textbase)
        var textsorti: EditText = findViewById(R.id.textsortie)
        var crypter: Button = findViewById(R.id.encoder)
        var decrypter: Button = findViewById(R.id.decoder)

        crypter.setOnClickListener {


        }

        private fun chiffrement(textentre: String):String {


        }
    }