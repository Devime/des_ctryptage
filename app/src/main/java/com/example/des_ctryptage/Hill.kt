package com.example.des_ctryptage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.Integer.valueOf

class Hill : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hill)
        var a: EditText = findViewById(R.id.a)
        var b: EditText = findViewById(R.id.b)
        var c: EditText = findViewById(R.id.c)
        var d: EditText = findViewById(R.id.d)
        var textentre: EditText = findViewById(R.id.textbase)
        var textsorti: EditText = findViewById(R.id.textsortie)
        var crypter: Button = findViewById(R.id.encoder)
        var decrypter: Button = findViewById(R.id.decoder)

        crypter.setOnClickListener {
            if (!check(a.text.toString(),b.text.toString(),c.text.toString(),d.text.toString())){
                println("NON")
                val toast = Toast.makeText(applicationContext,"matrice non valide", Toast.LENGTH_LONG)
                toast.show()
            }else{
                var out=chiffrement(a.text.toString(),b.text.toString(),c.text.toString(),d.text.toString(),textentre.text.toString())
                textsorti.setText(out)
            }

        }
    }

    private fun chiffrement(a: String, b: String, c: String, d: String, textentre: String):String {
        var couple: Array<String>
        var sb: StringBuilder = StringBuilder()
        var i = 0
        do {
            if (i == (textentre.length - 1)) {
                couple = listOf<String>(textentre[i].toString()," ").toTypedArray()
            } else {
                couple = listOf<String>(textentre[i].toString(), textentre[i+1].toString()).toTypedArray()
            }

            var newCouple=appliqueMatrice(couple,a,b,c,d)
            sb.append(newCouple[0])
            sb.append(newCouple[1])
            i+=2
        } while (i < textentre.length-1)
        return sb.toString()

    }

    private fun appliqueMatrice(couple: Array<String>, a: String, b: String, c: String, d: String): Array<String> {
        var newcouple: Array<String>
        var rangx1 = couple[0].toCharArray()[0].toInt()
        rangx1-=32
        var rangx2 = couple[1].toCharArray()[0].toInt()
        rangx2-=32
        var y1= ((valueOf(a)*rangx1+valueOf(b)*rangx2)%95)+32
        var y2=((valueOf(c)*rangx1+valueOf(d)*rangx2)%95)+32
        newcouple = listOf<String>(y1.toChar().toString(),y2.toChar().toString()).toTypedArray()
        return newcouple


    }

    private fun check(a: String, b: String, c: String, d: String): Boolean {

        var deter:Int=valueOf(a)*valueOf(d)-valueOf(b)*valueOf(c)
        if(deter%5==0 || deter%19==0){
            return false
        }
        return true

    }


}


