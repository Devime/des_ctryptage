package com.example.des_ctryptage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.lang.Integer.valueOf

class Vigenere : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vigenere)
        val textentre: EditText = findViewById(R.id.textbase)
        val textcle: EditText = findViewById(R.id.textcle)
        val textsorti: EditText = findViewById(R.id.textsortie)
        val crypter: Button = findViewById(R.id.encode)
        val decrypter: Button = findViewById(R.id.decode)
        val c = Cesar()

        crypter.setOnClickListener {
            val sortie = c.chiffrement(valueOf(textcle.text.toString()),textentre.text.toString())
            textsorti.setText(sortie)
            
        }
        decrypter.setOnClickListener {
            val de = c.chiffrement(-valueOf(textcle.text.toString()),textsorti.text.toString())
            textentre.setText(de)
        }
    }


}