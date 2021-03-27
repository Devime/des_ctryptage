package com.example.des_ctryptage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import java.lang.StringBuilder

class Transpositionrect : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transpositionrect)

        var tableLayout: TableLayout = findViewById(R.id.transtable)

        var btdo: Button = findViewById(R.id.transbt)
        var keytxt: EditText = findViewById(R.id.transkey)
        var txtin: EditText = findViewById(R.id.transin)
        var txtout: EditText = findViewById(R.id.transout)
        var btundo: Button = findViewById(R.id.transbtun)

        btdo.setOnClickListener {
            var out = maketabc(keytxt.text.toString(), txtin.text.toString())
            txtout.setText(out)
        }
        btundo.setOnClickListener {
            var tin = maketabd(keytxt.text.toString(), txtout.text.toString())
            txtin.setText(tin)
        }


    }

    private fun chiffrement(tab: Array<Array<String>>, col: Int, line: Int): String {
        var sb: StringBuilder = StringBuilder()
        var ind = 1
        var ref = 1
        for (i in 0 until col) {
            while (tab[ref][1] != (i + 1).toString()) {
                ref++
            }
            for (j in 2 until line) {
                if (tab[ref][j] != "Xx") {
                    sb.append(tab[ref][j])
                    //println(sb.toString())
                } else {
                    ref--
                }
            }
            ref = 0
        }
        println("sout len : " + sb.length)
        return sb.toString()
    }

    private fun maketabd(key: String, txt: String): String {

        var col = key.length
        var line = (txt.length / key.length) + 3
        println("col = $col & line = $line")
        var plus = txt.length % col

        println("line = $line et plus = $plus")
        var tab: Array<Array<String>> = Array(col) { Array(line) { " " } }
        var nbline = line - 3
        println("col : $col , line : $line , plus : $plus , nbline : $line , lenght : " + txt.length)

        for (i in 0 until col) {
            tab[i][0] = key[i].toString()
            tab[i][1] = "0"
        }
        var sorted = key.toSortedSet().toString()
        println(sorted)
        var p = 1
        var mem = 0
        for (i in sorted.indices) {
            for (j in 0 until col) {
                //println(tab[j][0]+"-->"+sorted[i])
                if (tab[j][0] == sorted[i].toString() && tab[j][1] == "0") {
                    tab[j][1] = (p).toString()
                    p++
                    for (y in 0 until nbline) {
                        if (mem + y < txt.length) {

                            tab[j][y + 2] = txt[mem + y].toString()
                        }
                    }
                    if (j + 1 <= plus) {
                        tab[j][line - 1] = txt[mem + nbline].toString()
                        mem++

                    }
                    mem += nbline
                }
            }
        }
        for (i in plus until col) {
            println("->$i----------")
            tab[i][line - 1] = "Xx"
        }
        /*var nc = 0
        var nl = 2
        for(j in txt.indices){
            tab[nc][nl] = txt[j].toString()
            nc++
            if (nc==col){
                nc=0
                nl++
            }
        }
        while(nc!=col){
            tab[nc][nl] ="Xx"
            nc++
        }*/

        affiche(tab, col, line)
        return dechiffrement(tab, col, line)
    }

    private fun dechiffrement(tab: Array<Array<String>>, col: Int, line: Int): String {
        var sb: StringBuilder = StringBuilder()
        var c = 0
        var l = 2
        while (tab[c][l].toString() != "Xx" || (c == (col - 1) && l == (line - 1))) {
            sb.append(tab[c][l])
            c++
            if (c == col) {
                c = 0
                l++
            }
        }


        return sb.toString()
    }

    private fun maketabc(key: String, txt: String): String {

        var col = key.length
        var line = (txt.length / key.length) + 2
        //println("col = $col & line = $line  et l : " + txt.length)
        if (txt.length % col != 0) line++
        var tab: Array<Array<String>> = Array(col) { Array(line) { " " } }
        for (i in 0 until col) {
            tab[i][0] = key[i].toString()
            tab[i][1] = "0"
        }
        var sorted = key.toSortedSet().toString()
        var p = 1

        for (i in sorted.indices) {
            for (j in 0 until col) {
                if (tab[j][0] == sorted[i].toString() && tab[j][1] == "0") {
                    tab[j][1] = (p).toString()
                    p++
                }
            }
        }
        var nc = 0
        var nl = 2
        for (j in txt.indices) {
            tab[nc][nl] = txt[j].toString()
            nc++
            if (nc == col) {
                nc = 0
                nl++
            }
        }
        if (nc > 0) {
            while (nc != col) {
                tab[nc][nl] = "Xx"
                nc++
            }
        }
        affiche(tab, col, line)
        return chiffrement(tab, col, line)
    }


    private fun affiche(tabi: Array<Array<String>>, col: Int, line: Int) {

        var tableLayout: TableLayout = findViewById(R.id.transtable)
        var row: TableRow
        var cell: TextView
        tableLayout.removeAllViews()
        for (li in 0 until line) {
            row = TableRow(this)
            for (c in 0 until col) {
                cell = TextView(this)
                cell.text = if ((tabi[c][li]) != "Xx") tabi[c][li] else " "
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