package com.example.des_ctryptage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
class Hill :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playfair)
        var btplfr: Button = findViewById<Button>(R.id.playfrbt)
        var keytxt: EditText = findViewById(R.id.playfrkey)
        btplfr.setOnClickListener {
            texte(keytxt.text.toString())
        }
    }
    fun texte (i:Int,cle:String,message:String){
        val alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var a,b,c,d
        var A=[a,b,c,d]//matrice
        var nbr = alphabet.length//longueur du texte (nbr de caractere)
        var determinantA = (A[0]*A[3]-A[1]*A[2])% nbr //determinant de la matrice A modulo n
        if (message.length % 2==1)
            message+=''


}