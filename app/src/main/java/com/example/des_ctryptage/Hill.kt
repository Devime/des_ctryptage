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
        val a: EditText = findViewById(R.id.a)
        val b: EditText = findViewById(R.id.b)
        val c: EditText = findViewById(R.id.c)
        val d: EditText = findViewById(R.id.d)
        val textentre: EditText = findViewById(R.id.textbase)
        val textsorti: EditText = findViewById(R.id.textsortie)
        val crypter: Button = findViewById(R.id.encoder)
        val decrypter: Button = findViewById(R.id.decoder)

        crypter.setOnClickListener {
            //on verifie que notre matrice est bien inversible modulo 95
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
                val out = chiffrement(
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
                //petit toast pour dire que la matrice ne convient pas
                val toast =
                    Toast.makeText(applicationContext, "matrice non valide", Toast.LENGTH_LONG)
                toast.show()
            } else {
                val out = dechiffremnt(
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

    //on applique la formule du chiffrement de hill
    private fun chiffrement(a: String, b: String, c: String, d: String, textentre: String): String {
        var couple: Array<String>
        val sb: StringBuilder = StringBuilder()
        var i = 0
        //on separe en couple de 2 elements , si le dernier est seul on ajoute un estpace avant le
        // chiffrement
        do {

            couple = if (i == (textentre.length - 1)) {
                listOf<String>(textentre[i].toString(), " ").toTypedArray()
            } else {
                listOf<String>(
                    textentre[i].toString(),
                    textentre[i + 1].toString()
                ).toTypedArray()
            }

            //on fais les calculs et on ajoute le resultat a notre stringbuilder

            val newCouple = appliqueMatrice(couple, a, b, c, d)
            sb.append(newCouple[0])
            sb.append(newCouple[1])
            i += 2
        } while (i <= textentre.length - 1)
        return sb.toString()

    }

    @RequiresApi(Build.VERSION_CODES.N)//pour le math.floorMod qui fais les calculs modulo
    // sans ça j'ai des résultats faux

    private fun dechiffremnt(a: String, b: String, c: String, d: String, txt: String): String {
        val deter: Int = valueOf(a) * valueOf(d) - valueOf(b) * valueOf(c)
        //on calcule l'inverse du determinant pr la matrice de dechiffrement
        val invers = inv(deter, 95)
        val newa: Int = valueOf(a) * invers
        val newb: Int = valueOf(b) * invers
        val newc: Int = valueOf(c) * invers
        val newd: Int = valueOf(d) * invers

        //comme en haut on sépare on fais les calculs et on ajoute au stringbuilder
        var couple: Array<String>
        val sb: StringBuilder = StringBuilder()
        var i = 0
        do {

            couple = if (i == (txt.length - 1)) {
                listOf<String>(txt[i].toString(), " ").toTypedArray()
            } else {
                listOf<String>(txt[i].toString(), txt[i + 1].toString()).toTypedArray()
            }


            val newCouple = appliqueMatrice2(couple, newa, newb, newc, newd)
            sb.append(newCouple[0])
            sb.append(newCouple[1])
            i += 2
        } while (i <= txt.length - 1)

        return sb.toString()
    }

    //2 a cause de % qui bug en déchiffrement
    @RequiresApi(Build.VERSION_CODES.N)
    private fun appliqueMatrice2(
        couple: Array<String>,
        a: Int,
        b: Int,
        c: Int,
        d: Int
    ): Array<String> {
        val newcouple: Array<String>

        var rangx1 = couple[0].toCharArray()[0].toInt()
        rangx1 -= 32
        var rangx2 = couple[1].toCharArray()[0].toInt()
        rangx2 -= 32
        val y1 = Math.floorMod(((d * rangx1) - (b * rangx2)), 95) + 32

        val y2 = Math.floorMod((a * rangx2 - c * rangx1), 95) + 32


        newcouple = listOf<String>(y1.toChar().toString(), y2.toChar().toString()).toTypedArray()
        return newcouple
    }


    //calcul l'inverse de x modulo i
    private fun inv(x: Int, i: Int): Int {
        var j = 0
        do {
            j++
        } while (((x * j) % i != 1) || (j == i))
        return j
    }

    //on applique les calculs
    private fun appliqueMatrice(
        couple: Array<String>,
        a: String,
        b: String,
        c: String,
        d: String
    ): Array<String> {
        val newcouple: Array<String>
        var rangx1 = couple[0].toCharArray()[0].toInt()
        rangx1 -= 32
        var rangx2 = couple[1].toCharArray()[0].toInt()
        rangx2 -= 32
        val y1 = ((valueOf(a) * rangx1 + valueOf(b) * rangx2) % 95) + 32
        val y2 = ((valueOf(c) * rangx1 + valueOf(d) * rangx2) % 95) + 32
        newcouple = listOf<String>(y1.toChar().toString(), y2.toChar().toString()).toTypedArray()
        return newcouple

    }

    private fun check(a: String, b: String, c: String, d: String): Boolean {

        val deter: Int = valueOf(a) * valueOf(d) - valueOf(b) * valueOf(c)
        if (deter % 5 == 0 || deter % 19 == 0) {
            return false
        }
        return true

    }


}


