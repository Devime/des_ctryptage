package com.example.des_ctryptage

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Cesar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cesar)

        val Btchiffrement = findViewById<Button>(R.id.cesarBtcrypter)
        val Btdechiffrement = findViewById<Button>(R.id.cesarBtdecrypter)
        val txtin = findViewById<EditText>(R.id.cesartxin)
        val txtout = findViewById<EditText>(R.id.cesartxout)

        Btchiffrement.setOnClickListener {
            val out = chiffrement(3, txtin.text.toString())
            println(txtin.text.toString())
            txtout.setText(out)
        }

        Btdechiffrement.setOnClickListener {
            val out = chiffrement(-3, txtout.text.toString())
            println(txtin.text.toString())
            txtin.setText(out)
        }

    }

    //on vas prendre le rang de chaque element du string en entre et décaler son rang de i ( pour
    //césar ça sera 3 , je definie cette fonction avec i pour pouvoire l'utiliser pour Vigenere).
    //pour le déchiffrement il suffira de faire -i.
    fun chiffrement(i: Int, txt: String): String {

        val sb: StringBuilder = StringBuilder()
        var temp: Int
        //on parcour chaque elements
        txt.forEach {
            // on passe en ascii , puis on fais le décalage
            temp = it.toByte().toInt()
            temp = (temp - 32) + i
            temp %= 95
            //si on est negatif on par de la fin de notre alphabet
            temp = if (temp >= 0) {
                temp + 32
            } else {
                temp + 126
            }
            //on ajout a notre stringbuilder pour finir
            sb.append(temp.toChar().toString())
        }
        return sb.toString()
    }


}