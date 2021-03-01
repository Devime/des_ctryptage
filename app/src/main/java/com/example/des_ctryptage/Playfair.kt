package com.example.des_ctryptage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import com.google.android.material.tabs.TabLayout

class Playfair : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playfair)

        var btplfr: Button = findViewById<Button>(R.id.playfrbt)
        var keytxt: EditText = findViewById(R.id.playfrkey)
        var txtin: EditText = findViewById(R.id.playin)
        var txtout: EditText = findViewById(R.id.playout)


        btplfr.setOnClickListener {
            var tab =dotab(keytxt.text.toString())
            chiffrement(tab,keytxt.toString())
        }
    }

    fun dotab(key: String): Array<Array<String>> {
        val tab: Array<Array<String>>
        var nbcol = key.length
        var nbl = (25 / nbcol)
        if (nbcol + nbl != 25) {
            nbl++
        }
        var o = 0
        tab = Array(nbcol) { Array(nbl) { " " } }
        println("-+-+-+-+-++-+-+-+-+-->nbcol = $nbcol >>>>>->nbl = $nbl  ")
        for (c in 0 until nbl) {
            for (i in 0 until nbcol) {

                if (c == 0) {
                    tab[i][c] = key[i].toString()
                } else {
                    if (key.contains((o + 97).toChar())) {
                        o++
                    }
                    if ((97 + o) == 119) {
                        o++
                    }
                    if ((o + 97) <= 122) {
                        tab[i][c] = (o + 97).toChar().toString()
                        o++
                    } else {
                        tab[i][c] = "_"
                    }


                }
            }
        }
        affiche(tab,nbcol,nbl)
        afficheblanc()
        return maketab(tab, nbcol, nbl)

    }


    private fun afficheblanc(){
        var tableLayout:TableLayout=findViewById(R.id.playtable)
        var cell:TextView
        var row:TableRow = TableRow(this)
        for(c in 0 until 5){
            cell = TextView(this)
            cell.text= "\\/"
            cell.gravity= Gravity.CENTER
            cell.textSize= 20F
            cell.layoutParams= TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                1F
            )
            row.addView(cell)
        }
        tableLayout.addView(row)
    }

    private fun affiche(tabi: Array<Array<String>>, col: Int, line: Int) {

        var tableLayout:TableLayout=findViewById(R.id.playtable)
        var row:TableRow
        var cell:TextView
        for (li in 0 until line){
            row = TableRow(this)
            for(c in 0 until col){
                cell = TextView(this)
                cell.text= if ((tabi[c][li])!="_") tabi[c][li] else " "
                cell.gravity= Gravity.CENTER
                cell.textSize= 20F
                cell.layoutParams= TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                    1F
                )
                row.addView(cell)
            }
            tableLayout.addView(row)
        }
    }


    private fun maketab(tab: Array<Array<String>>, col: Int, line: Int):Array<Array<String>> {
        var finaltab: Array<Array<String>> = Array(5) { Array(5) { " " } }
        var i=0
        var j=0
        for (l in 0 until 5) {
            for (c in 0 until 5) {
                if (tab[i][j]!="_"){
                    var d=tab[i][j]
                    println("----------->i : $i---------------------->j : $j")
                    finaltab[c][l]=tab[i][j]
                    j++
                    if (j==line){
                        j=0
                        i++

                    }
                }else{
                    println("----------->i : $i---------------------->j : $j")
                    j++
                    if (j==line){
                        j=0
                        i++

                    }
                    finaltab[c][l]=tab[i][j]
                    j++
                    if (j==line){
                        j=0
                        i++

                    }
                }
            }
        }
        affiche(finaltab,5,5)
        return finaltab
    }


    private fun chiffrement(tab: Array<Array<String>>,txt:String){
        var cop:Array<String> = Array(2) { "" }
        cop[0]="a"
        cop[1]="b"
        find(tab,cop)
    }

    private fun find(tab: Array<Array<String>>,couple:Array<String>): Array<String> {
        var coo:Array<String> = Array(4) { "" }
        var i = 0
        var j = 0
        var y = 0
        for (k in 0 until 2){
            if(couple[k]=="w")couple[k]="v"
            while (tab[i][j]!=couple[k]){
                j++
                if (j==4){
                    i++
                    j=0
                }
            }
            coo[y]=i.toString()
            y++
            coo[y]=j.toString()
            y++
            println(coo[0])
            println(coo[1])
        }

        return coo
    }


}


