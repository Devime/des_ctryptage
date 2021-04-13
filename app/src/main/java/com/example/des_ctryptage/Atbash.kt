package com.example.des_ctryptage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Atbash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atbash)

        val Btchiffrement = findViewById<Button>(R.id.atbashBtchiff)
        val Btdechiffrement = findViewById<Button>(R.id.atbashBtdechif)
        val txtin = findViewById<EditText>(R.id.atbashtxin)
        val txtout = findViewById<TextView>(R.id.atbashtxout)



        Btchiffrement.setOnClickListener {
            val out = chiffrement(txtin.text.toString())
            println(txtin.text.toString())
            txtout.text = out
        }

        Btdechiffrement.setOnClickListener {
            val out = dechiffrement(txtin.text.toString())
            println(txtin.text.toString())
            txtout.text = out
        }

    }

    //on prends en entree un string et pour caque element on prends son rang opposer dans tous les caractère affichable
    private fun chiffrement(txt: String): String {
        var c = 0
        val sb = StringBuilder()
        for (i in txt.indices) {
            //if (!txt[i].equals(" "))
            txt[i].toByte().toInt().also { c = it }
            c = (126 - c) + 32
            val char = c.toChar().toString()
            sb.append(char)
        }
        return sb.toString()
    }

    //sa reviens au même
    private fun dechiffrement(txt: String): String {
        return chiffrement(txt)
    }
}