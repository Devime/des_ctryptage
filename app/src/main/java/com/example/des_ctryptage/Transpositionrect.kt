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
            var tab = maketab(keytxt.text.toString(),txtin.text.toString())
            var txt = chiffrement(tab,keytxt.text.toString().length)


        }


    }

    private fun chiffrement(tab: Array<Array<String>>,col:Int): String {
        var sb: StringBuilder = StringBuilder()
        for ( i in 0 until col){

        }
        return sb.toString()
    }

    private fun maketab(key:String, txt: String): Array<Array<String>> {

        var col= key.length
        var line = (txt.length/key.length)+3
        println( "col = $col & line = $line")
        if (txt.length%col!=0) line++
        var tab: Array<Array<String>> = Array(col) { Array(line) { " " } }

        for (i in 0 until col){
            tab[i][0]= key[i].toString()
            tab[i][1]= "0"
        }
        var sorted=key.toSortedSet().toString()
        println(sorted)
        var p=1
        for (i in sorted.indices){
            for(j in 0 until col) {
                println(tab[j][0]+"-->"+sorted[i])
                if (tab[j][0] == sorted[i].toString() && tab[j][1] == "0"){
                    tab[j][1] = (p).toString()
                    p++
                }
            }
        }
        var nc = 0
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
        }
        affiche(tab,col,line)
        return tab
    }


    private fun affiche(tabi: Array<Array<String>>, col: Int, line: Int) {

        var tableLayout: TableLayout = findViewById(R.id.transtable)
        var row: TableRow
        var cell: TextView
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