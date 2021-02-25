package com.example.des_ctryptage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Playfair : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playfair)

        var btplfr: Button = findViewById<Button>(R.id.playfrbt)
        var keytxt: EditText = findViewById(R.id.playfrkey)

        btplfr.setOnClickListener {
            dotab(keytxt.text.toString())
        }
    }

    fun dotab(key:String){
        val tab:Array<Array<Int>>
        var nbr = key.length
        var nbc = (25/nbr)+1
        tab = Array(nbr) { Array(nbc) { 0 } }
        for (row in tab){//todo faire avec des indices
            println(row.toString())
            for (c in row){
                println(c)
            }
        }
    }
}