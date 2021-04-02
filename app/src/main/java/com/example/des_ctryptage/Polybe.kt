package com.example.des_ctryptage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import java.lang.StringBuilder
import java.lang.Integer.valueOf

class Polybe : AppCompatActivity() {

    var carre: Array<Array<String>> = Array(6) { Array(6) { " " } }

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




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_polybe)



        val btdo: Button = findViewById(R.id.polyfrbt)
        val txtin: EditText = findViewById(R.id.polyin)
        val txtout: EditText = findViewById(R.id.polyout)
        val btundo: Button = findViewById(R.id.polyfrbtun)

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


    }

    fun chiffrement(txt: String): String {
        val sb: StringBuilder = StringBuilder()

        txt.forEach {
            sb.append(find((it.toString())))
        }

        return sb.toString()
    }


    fun find(egg: String): String {
        val s = StringBuilder()
        var i = 0
        var j = 0
        println("----------------------searching : $egg")
        while (carre[i][j] != egg) {
            //println("j=$j i=$i ->[$egg] ?= [${carre[i][j]}]")
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