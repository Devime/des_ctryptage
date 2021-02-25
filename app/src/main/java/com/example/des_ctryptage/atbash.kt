package com.example.des_ctryptage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class atbash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atbash)

        var Btchiffrement = findViewById<Button>(R.id.atbashBtchiff)
        var Btdechiffrement = findViewById<Button>(R.id.atbashBtdechif)
        var txtin = findViewById<EditText>(R.id.atbashtxin)
        var txtout = findViewById<TextView>(R.id.atbashtxout)



        Btchiffrement.setOnClickListener {
            var out = chiffrement(txtin.text.toString())
            println(txtin.text.toString())
            txtout.text = out
        }

        Btdechiffrement.setOnClickListener {
            var out = dechiffrement(txtin.text.toString())
            println(txtin.text.toString())
            txtout.text = out
        }

    }

    private fun chiffrement(txt: String): String {
        val out = ""
        var c:Int=0
        val sb = StringBuilder()

        for (i in txt.indices) {
            if (!txt[i].equals(" "))
                println(txt[i])
                c = txt[i].toByte().toInt()
                println("******************************$c")
                c = (25 - (c - 97)) + 97
                println("******************************$c")
                val char = c.toChar().toString()
                sb.append(char)
                println("=======================$char")
                println("-----------------${sb.toString()}")
        }
        return sb.toString()
    }

    private fun dechiffrement(txt: String): String {
        return chiffrement(txt)
    }
}