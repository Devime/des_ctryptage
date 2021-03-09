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
                chiffrement(a.text.toString(),b.text.toString(),c.text.toString(),d.text.toString(),textentre.text.toString())
            }

        }
    }

    private fun chiffrement(a: String, b: String, c: String, d: String, textentre: String) {
        //TODO

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
    var a, b, c, d


}