package com.example.des_ctryptage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import java.lang.StringBuilder
import java.lang.Integer.valueOf
import kotlin.random.Random.Default.nextInt

class Polybe : AppCompatActivity() {

    var carre: Array<Array<String>> = Array(6) { Array(6) { " " } }

    //on remplis un carre de a a z et de 0 a 9
    fun fill() {
        var x = 97
        for (i in 0 until 6) {
            for (j in 0 until 6) {
                carre[j][i] = x.toChar().toString()

                x = if (x >= 122) {
                    48
                } else {
                    x + 1
                }
            }
        }
    }

    //on intervertie des elements aleatoirement
    fun random(){
        var random : Int
        var temp : String
        for (i in 0 until 6) {
            for (j in 0 until 6) {
                random = nextInt(35)
                temp = carre[j][i]
                val c = random/6
                val l = (random%6)
                println("rand = $random , c = $c , l = $l")

                carre[j][i] = carre[c][l]
                carre[c][l] = temp
            }
        }

    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_polybe)



        val btdo: Button = findViewById(R.id.polyfrbt)
        val txtin: EditText = findViewById(R.id.polyin)
        val txtout: EditText = findViewById(R.id.polyout)
        val btundo: Button = findViewById(R.id.polyfrbtun)
        val btdefault: Button = findViewById(R.id.polybtdef)
        val  btrand: Button = findViewById(R.id.polybtrand)

        fill()
        affiche(carre, 6, 6)

        btdo.setOnClickListener {
            val out: String = chiffrement(txtin.text.toString())
            txtout.setText(out)
        }

        btundo.setOnClickListener {
            val out = dechiffrement(txtout.text.toString())
            txtin.setText(out)
        }

        btdefault.setOnClickListener {
            fill()
            affiche(carre,6,6)
        }

        btrand.setOnClickListener {
            random()
            affiche(carre,6,6)
        }



    }


    //on prends simplement les coordonnees des elelement
    fun chiffrement(txt: String): String {
        val sb = StringBuilder()

        txt.forEach {
            sb.append(find((it.toString())))
        }

        return sb.toString()
    }


    //on parcours le tableau jusqu'a trouver notre element
    fun find(egg: String): String {
        val s = StringBuilder()
        var i = 0
        var j = 0
        while (carre[i][j] != egg) {
            i++
            if (i == 6) {
                j++
                i = 0
            }
        }
        i++
        j++
        s.append(j.toString())
        s.append(i.toString())


        return s.toString()
    }

    //on affiche l'elments grace aux coordonnées
    fun dechiffrement(txt:String):String{
        val sb = StringBuilder()
        var i=0
        var ligne : Int
        var col : Int
        do{
            ligne = valueOf(txt[i].toString())-1
            col = valueOf(txt[i+1].toString())-1

            sb.append(carre[col][ligne])

            i+=2
        }while (i<txt.length-1)


        return sb.toString()
    }


    fun affiche(tabi: Array<Array<String>>, col: Int, line: Int) {

        val tableLayout: TableLayout = findViewById(R.id.polytable)
        tableLayout.removeAllViews()
        var row: TableRow
        var cell: TextView
        for (li in 0 until line) {
            row = TableRow(this)
            for (c in 0 until col) {
                cell = TextView(this)
                cell.text = if ((tabi[c][li]) != "_") tabi[c][li] else " "
                cell.gravity = Gravity.CENTER
                cell.textSize = 20F
                cell.layoutParams = TableRow.LayoutParams(
                    0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                    1F
                )
                row.addView(cell)
            }
            tableLayout.addView(row)
        }
    }

}