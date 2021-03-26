package com.example.des_ctryptage

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Cesar: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cesar)

        var Btchiffrement = findViewById<Button>(R.id.cesarBtcrypter)
        var Btdechiffrement = findViewById<Button>(R.id.cesarBtdecrypter)
        var txtin = findViewById<EditText>(R.id.cesartxin)
        var txtout = findViewById<TextView>(R.id.cesartxout)

        Btchiffrement.setOnClickListener {
            var out = crypter(3,txtin.text.toString())
            println(txtin.text.toString())
            txtout.text = out
        }

        Btdechiffrement.setOnClickListener {
            var out = decrypter(3,txtin.text.toString())
            println(txtin.text.toString())
            txtout.text = out
        }

    }
    var alphabet=charArrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
    //fonction pour crypter
    fun crypter(n: Int, message_entre: String): String {
        val message=message_entre.toCharArray()
        val message_sortie=CharArray(message.size)
        var position_de_depart: Int
        var nouvelle_position: Int
        for (i in message.indices) {
            position_de_depart= depart(message[i], alphabet)
            nouvelle_position= newp(position_de_depart, n)
            if (nouvelle_position==-1)
                message_sortie[i]=' '
            else
                message_sortie[i]= alphabet[nouvelle_position]
        }
        return String(message_sortie)//conversion en string d'un tableau de char
    }
//fonction pour décrypter
    fun decrypter(n: Int, message_entre: String): String {
        val message=message_entre.toCharArray()
        val message_sortie=CharArray(message.size)
        var position_de_depart: Int
        var nouvelle_position: Int
        for (i in message.indices) {
            position_de_depart= depart(message[i], alphabet)
            nouvelle_position= newp(position_de_depart, -n)
            if (nouvelle_position==-1)
                message_sortie[i]=' '
            else message_sortie[i]= alphabet[nouvelle_position]
        }
        return String(message_sortie)
    }
    //position de départ
    private fun depart(c: Char, tab: CharArray): Int {
        for (i in tab.indices) {
            if (tab[i] == c)
                return i
        }
        return -1
    }
//nouvelle position
    private fun newp(pos: Int, n: Int): Int {
        var nouvelle_position=pos
        if (pos<=-1) {
            nouvelle_position=-1
        } else {
            var i=0
            while (i<absolue(n)) {
                if (n<0) {
                    if (nouvelle_position-1==-1) nouvelle_position=25
                    else
                        nouvelle_position--
                } else {
                    if (nouvelle_position+1>=25)
                        nouvelle_position=0
                    else
                        nouvelle_position++
                }
                i++
            }
        }
        return nouvelle_position
    }
    // Valeur absolue
    fun absolue(a: Int): Int {
        return if (a>=0)
            a
        else
            -a
    }

}