package com.example.des_ctryptage.com.example.des_ctryptage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.des_ctryptage.R
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
                couple = listOf<String>(textentre[i].toString(), "z").toTypedArray()
            } else {
                couple = listOf<String>(textentre[i].toString(), textentre[i+1].toString()).toTypedArray()
            }

            var new_couple=applique_matrice(couple,a,b,c,d)
            sb.append(new_couple[0])
            sb.append(new_couple[1])
            i+=2
        } while (i < textentre.length)
        return sb.toString()

    }

    private fun applique_matrice(couple: Array<String>, a: String, b: String, c: String, d: String): Array<String> {
        var new_couple: Array<String>
        var rangx1 = couple[0].toByte().toInt()
        rangx1-=97
        var rangx2 = couple[1].toByte().toInt()
        rangx2-=97
        var y1= ((valueOf(a[0].toString())*rangx1+valueOf(b[0].toString())*rangx2)%26)+97
        var y2=((valueOf(c[0].toString())*rangx1+valueOf(d[0].toString())*rangx2)%26)+97
        new_couple = listOf<String>(y1.toChar().toString(),y2.toChar().toString()).toTypedArray()
        return new_couple


    }
}

fun check(a: String, b: String, c: String, d: String): Boolean {

    var deter:Int=valueOf(a)*valueOf(d)-valueOf(b)*valueOf(c)
    if(deter%2==0 || deter%13==0){
        return false
    }
    return true

}

fun texte(i: Int, cle: String, message: String) {



}