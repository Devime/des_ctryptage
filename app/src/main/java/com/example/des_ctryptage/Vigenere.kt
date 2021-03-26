package com.example.des_ctryptage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.lang.StringBuilder

class Vigenere : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vigenere)
        var textentre: EditText = findViewById(R.id.textbase)
        var textcle: EditText = findViewById(R.id.textcle)
        var textsorti: EditText = findViewById(R.id.textsortie)
        var crypter: Button = findViewById(R.id.encode)
        var decrypter: Button = findViewById(R.id.decode)

        crypter.setOnClickListener {
            var sortie=encoder(textentre.text.toString(),textcle.text.toString())
            textsorti.setText(sortie)
            
        }
        decrypter.setOnClickListener {
            var de=decoder(encoder(textentre.text.toString(),textcle.text.toString()),textcle.text.toString() )
            textsorti.setText(de)
        }
    }
    fun encoder(s: String, key: String): String {
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

    }

}