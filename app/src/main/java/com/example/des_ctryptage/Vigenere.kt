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
            //val sortie=encoder(textentre.text.toString(),textcle.text.toString())
            println()
            val sortie = c.chiffrement(valueOf(textcle.text.toString()),textentre.text.toString())
            textsorti.setText(sortie)
            
        }
        decrypter.setOnClickListener {
            //val de=decoder(encoder(textentre.text.toString(),textcle.text.toString()),textcle.text.toString() )
            val de = c.chiffrement(-valueOf(textcle.text.toString()),textsorti.text.toString())
            textentre.setText(de)
        }
    }
    /*fun encoder(s: String, key: String): String {
        val builder = StringBuilder()
        for (i in 0 until s.length) {
            require(!(s[i].toInt() < 65 || s[i].toInt() > 123)) {
            }
            val enco= if (s[i].toInt() + cle(key, i) > 123) (s[i].toInt() + cle(key, i) - 26).toChar() else (s[i].toInt() + cle(key, i)).toChar()
            builder.append(enco)
        }
        return builder.toString()
    }
    fun decoder(s: String, key: String): String {
        val builder = StringBuilder()
        for (i in 0 until s.length) {
            require(!(s[i].toInt() < 97 || s[i].toInt() > 123  ))  {
            }//verifie si tout les caractére sont dan sla table ascii utilisé
            val deco = if (s[i].toInt() - cle(key, i) < 97) (s[i].toInt() - cle(key, i) + 26).toChar() else (s[i].toInt() - cle(key, i)).toChar()
            builder.append(deco)
        }
        return builder.toString()
    }
    fun cle(key: String, i: Int): Int {
        require(!(key[i % key.length].toInt() < 97 || key[i % key.length].toInt() >123)) {
        }
        return key[i % key.length].toInt() - 97

    }*/

}