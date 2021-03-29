package com.example.des_ctryptage

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.lang.Integer.valueOf

class Hill : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
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
            if (!check(
                    a.text.toString(),
                    b.text.toString(),
                    c.text.toString(),
                    d.text.toString()
                )
            ) {
                println("NON")
                val toast =
                    Toast.makeText(applicationContext, "matrice non valide", Toast.LENGTH_LONG)
                toast.show()
            } else {
                var out = chiffrement(
                    a.text.toString(),
                    b.text.toString(),
                    c.text.toString(),
                    d.text.toString(),
                    textentre.text.toString()
                )
                textsorti.setText(out)
            }

        }

        decrypter.setOnClickListener {
            if (!check(
                    a.text.toString(),
                    b.text.toString(),
                    c.text.toString(),
                    d.text.toString()
                )
            ) {
                println("NON")
                val toast =
                    Toast.makeText(applicationContext, "matrice non valide", Toast.LENGTH_LONG)
                toast.show()
            } else {
                var out = dechiffremnt(
                    a.text.toString(),
                    b.text.toString(),
                    c.text.toString(),
                    d.text.toString(),
                    textsorti.text.toString()
                )
                textentre.setText(out)
            }

        }
    }

    private fun chiffrement(a: String, b: String, c: String, d: String, textentre: String): String {
        var couple: Array<String>
        var sb: StringBuilder = StringBuilder()
        var i = 0
        do {

            couple = if (i == (textentre.length - 1)) {
                listOf<String>(textentre[i].toString(), " ").toTypedArray()
            } else {
                listOf<String>(
                    textentre[i].toString(),
                    textentre[i + 1].toString()
                ).toTypedArray()
            }

            println("i = $i -> ${couple[0]},${couple[1]} ")

            var newCouple = appliqueMatrice(couple, a, b, c, d)
            sb.append(newCouple[0])
            sb.append(newCouple[1])
            i += 2
        } while (i <= textentre.length - 1)
        return sb.toString()

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun dechiffremnt(a: String, b: String, c: String, d: String, txt: String): String {
        val deter: Int = valueOf(a) * valueOf(d) - valueOf(b) * valueOf(c)
        println(deter)
        val invers = inv(deter, 95)
        val newa: Int = valueOf(a) * invers
        val newb: Int = valueOf(b) * invers
        val newc: Int = valueOf(c) * invers
        val newd: Int = valueOf(d) * invers


        var couple: Array<String>
        val sb: StringBuilder = StringBuilder()
        var i = 0
        do {

            couple = if (i == (txt.length - 1)) {
                listOf<String>(txt[i].toString(), " ").toTypedArray()
            } else {
                listOf<String>(txt[i].toString(), txt[i + 1].toString()).toTypedArray()
            }

            println("i = $i -> ${couple[0]},${couple[1]} ")

            val newCouple = appliqueMatrice2(couple, newa, newb, newc, newd)
            sb.append(newCouple[0])
            sb.append(newCouple[1])
            i += 2
        } while (i <= txt.length - 1)

        return sb.toString()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun appliqueMatrice2(
        couple: Array<String>,
        a: Int,
        b: Int,
        c: Int,
        d: Int
    ): Array<String> {
        var newcouple: Array<String>
        //println("-----------------------")
        couple.forEach {
            print(it)
        }
        var rangx1 = couple[0].toCharArray()[0].toInt()
        //println("\n $rangx1")
        rangx1 -= 32
        var rangx2 = couple[1].toCharArray()[0].toInt()
        //println(rangx2)
        rangx2 -= 32
        var y1 = Math.floorMod(((d * rangx1) - (b * rangx2)) , 95) + 32
        val y11 = d*rangx1
        val y12 = b*rangx2
        val y111 = y11 - y12
        var y2 = Math.floorMod((a * rangx2 - c * rangx1) , 95) + 32
        //println(" $y11 - $y12 = $y111 ->${Math.floorMod(y111,95)}")
        //println("y1 = $y1  y2 = $y2")
        //println("-----------------------")

        newcouple = listOf<String>(y1.toChar().toString(), y2.toChar().toString()).toTypedArray()
        return newcouple
    }

    private fun mod(a: Int, invers: Int): Int {
        println("a : $a   -> " + a * invers + " -> " + (a * invers) % 95)
        return (a * invers) % 95
    }

    private fun inv(x: Int, i: Int): Int {
        var j = 0
        do {
            j++
            println("" + x * j + "->j = $j  - " + (x * j) % i)
        } while (((x * j) % i != 1) || (j == i))
        return j
    }

    private fun appliqueMatrice(
        couple: Array<String>,
        a: String,
        b: String,
        c: String,
        d: String
    ): Array<String> {
        var newcouple: Array<String>
        var rangx1 = couple[0].toCharArray()[0].toInt()
        rangx1 -= 32
        var rangx2 = couple[1].toCharArray()[0].toInt()
        rangx2 -= 32
        var y1 = ((valueOf(a) * rangx1 + valueOf(b) * rangx2) % 95) + 32
        var y2 = ((valueOf(c) * rangx1 + valueOf(d) * rangx2) % 95) + 32
        newcouple = listOf<String>(y1.toChar().toString(), y2.toChar().toString()).toTypedArray()
        return newcouple


    }

    private fun check(a: String, b: String, c: String, d: String): Boolean {

        var deter: Int = valueOf(a) * valueOf(d) - valueOf(b) * valueOf(c)
        if (deter % 5 == 0 || deter % 19 == 0) {
            return false
        }
        return true

    }


}


