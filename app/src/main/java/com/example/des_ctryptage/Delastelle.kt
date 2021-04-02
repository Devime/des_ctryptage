package com.example.des_ctryptage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.lang.Integer.valueOf
import java.lang.StringBuilder

class Delastelle : AppCompatActivity() {

    val c = Polybe()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delastelle)



        c.fill()

        val btdo: Button = findViewById(R.id.delbt)
        val txtin: EditText = findViewById(R.id.delin)
        val key: EditText = findViewById((R.id.delkey))
        val txtout: EditText = findViewById(R.id.delout)
        val btundo: Button = findViewById(R.id.delbtun)

        btdo.setOnClickListener {
            val out: String = chiffrement(txtin.text.toString(), key.text.toString())
        }

    }

    private fun chiffrement(txt: String, key: String): String {
        val sb: StringBuilder = StringBuilder()
        val subsb : StringBuilder = StringBuilder()
        val totab : StringBuilder = StringBuilder()
        val kei = valueOf(key)

        totab.append(txt)
        val nbdiv: Int = if (totab.length % kei != 0) {
            (totab.length / kei) + 1
        } else {
            totab.length / kei
        }
        println(" txt = ${txt.length}  , key = $kei , sbl = ${totab.length} , tototot = ${(kei*nbdiv)} ")
        while (totab.length!=(kei*nbdiv)){
            totab.append("x")
            println("added sbl = ${totab.length} , tototot = ${(kei*nbdiv)} ")
        }

        val tab = c.chiffrement(totab.toString())//string
        val tab1: Array<String> = Array(tab.length / 2) { " " }
        val tab2: Array<String> = Array(tab.length / 2) { " " }
        tab.forEach {
            print("$it")
        }
        println("<-")
        var k = 0
        for (i in tab.indices step 2) {
            tab1[k] = tab[i].toString()
            println("***[${tab1[k]}]****")
            tab2[k] = tab[i + 1].toString()
            k++
        }
        ///TODO découper les tab en n section de longueur kei puis faire la merde et afficher les lettre voilà bisous !



        for (div in 0 until nbdiv){
            var i = 0
            do{
                subsb.append(tab1[(div*kei)+i])//sa a garde k !
                println(" $i---> ${subsb.toString()}")
                i++
            }while (i!=kei)
            i=0
            do{
                subsb.append(tab2[(div*kei)+i])
                i++
            }while (i!=kei)
            println("--$div / $nbdiv ->${subsb.toString()}")
        }
        sb.append(c.dechiffrement(subsb.toString()))

        println(sb.toString())
        return sb.toString()
    }

}