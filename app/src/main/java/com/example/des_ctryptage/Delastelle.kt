package com.example.des_ctryptage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.lang.Integer.valueOf
import kotlin.text.StringBuilder

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
            txtout.setText(out)
        }
        btundo.setOnClickListener {
            val out : String = dechiffrement(txtout.text.toString(),key.text.toString())
            txtin.setText(out)
        }
    }

    private fun dechiffrement(txt: String, key: String): String {

        val tab1: Array<String> = Array(txt.length) { " " }
        val tab2: Array<String> = Array(txt.length) { " " }
        val prestri = c.chiffrement(txt)
        val nbdiv = txt.length/ valueOf(key)
        val nbkey = valueOf(key)
        println(nbdiv)
        var i = 0
        var k=0

        println(" pre : $prestri")
        for (div in 0 until nbdiv) {
            println("div---->${div}")
            for(j in nbkey*2*div until nbkey*2*div+nbkey ){
                tab1[i]= prestri[j].toString()
                println(" i = $i , x = $j/ ${prestri.length}")
                println("p: ${prestri[j].toString()} -->1: ${tab1[i]}")
                i++
            }
            for(j in nbkey*2*div+nbkey until  nbkey*2*div+2*nbkey ){
                tab2[k]= prestri[j].toString()
                println(" j = $k , x = $j / ${prestri.length}")
                println("p: ${prestri[j].toString()} -->1: ${tab2[k]}")
                k++
            }
        }
        val subsb : java.lang.StringBuilder = java.lang.StringBuilder()
        for (c in tab1.indices){
            subsb.append(tab1[c])
            subsb.append(tab2[c])
            println(": : : > ${tab1[c]} + ${tab2[c]} =$subsb")
        }


        return c.dechiffrement(subsb.toString())
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