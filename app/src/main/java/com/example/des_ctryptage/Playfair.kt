package com.example.des_ctryptage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import java.lang.Integer.valueOf
import java.lang.StringBuilder

class Playfair : AppCompatActivity() {

    lateinit var tableLayout: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playfair)

        tableLayout = findViewById(R.id.playtable)

        var btplfr: Button = findViewById(R.id.playfrbt)
        var keytxt: EditText = findViewById(R.id.playfrkey)
        var txtin: EditText = findViewById(R.id.playin)
        var txtout: EditText = findViewById(R.id.playout)
        var btundo: Button = findViewById(R.id.playfrbtun)


        btplfr.setOnClickListener {
            tableLayout.removeAllViews()
            var tab = dotab(keytxt.text.toString())
            var texto = chiffrement(tab, txtin.text.toString())
            txtout.setText(texto)
        }
        btundo.setOnClickListener {
            tableLayout.removeAllViews()
            var tab = dotab(keytxt.text.toString())
            var texto = dechiffrement(tab, txtout.text.toString())
            txtin.setText(texto)
        }
    }


    fun dotab(key: String): Array<Array<String>> {
        val tab: Array<Array<String>>
        var nbcol = key.length
        var nbl = (36 / nbcol)
        if (nbcol + nbl != 36) {
            nbl++
        }
        var o = 0
        var n = 0
        tab = Array(nbcol) { Array(nbl) { " " } }
        //println("-+-+-+-+-++-+-+-+-+-->nbcol = $nbcol >>>>>->nbl = $nbl  ")
        for (c in 0 until nbl) {
            for (i in 0 until nbcol) {

                if (c == 0) {
                    tab[i][c] = key[i].toString()
                } else {
                    if (key.contains((o + 97).toChar())) {
                        o++
                    }
                    if ((o + 97) <= 122) {
                        tab[i][c] = (o + 97).toChar().toString()
                        o++
                    } else {
                        if (n <= 9) {
                            tab[i][c] = n.toString()
                            n++
                        } else {
                            tab[i][c] = "_"
                        }
                    }


                }
            }
        }
        affiche(tab, nbcol, nbl)
        afficheblanc()
        return maketab(tab, nbcol, nbl)

    }


    private fun afficheblanc() {

        var cell: TextView
        var row: TableRow = TableRow(this)
        for (c in 0 until 6) {
            cell = TextView(this)
            cell.text = "\\/"
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

    private fun affiche(tabi: Array<Array<String>>, col: Int, line: Int) {

        var tableLayout: TableLayout = findViewById(R.id.playtable)
        var row: TableRow
        var cell: TextView
        for (li in 0 until line) {
            row = TableRow(this)
            for (c in 0 until col) {
                cell = TextView(this)
                cell.text = if ((tabi[c][li]) != "_") tabi[c][li] else " "
                println(cell.text)
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


    private fun maketab(tab: Array<Array<String>>, col: Int, line: Int): Array<Array<String>> {
        var finaltab: Array<Array<String>> = Array(6) { Array(6) { " " } }
        var i = 0
        var j = 0
        for (l in 0 until 6) {
            for (c in 0 until 6) {
                if (tab[i][j] != "_") {
                    var d = tab[i][j]
                    //println("----------->i : $i---------------------->j : $j")
                    finaltab[c][l] = tab[i][j]
                    j++
                    if (j == line) {
                        j = 0
                        i++

                    }
                } else {
                    //println("----------->i : $i---------------------->j : $j")
                    j++
                    if (j == line) {
                        j = 0
                        i++

                    }
                    finaltab[c][l] = tab[i][j]
                    j++
                    if (j == line) {
                        j = 0
                        i++

                    }
                }
            }
        }
        affiche(finaltab, 6, 6)
        return finaltab
    }


    private fun chiffrement(tab: Array<Array<String>>, txt: String): String {
        var coordonnees: Array<String>
        var couple: Array<String>
        var sb: StringBuilder = StringBuilder()
        var i = 0
        do {
            if (i == (txt.length - 1)) {
                couple = listOf<String>(txt[i].toString(), "z").toTypedArray()
            } else if (txt[i].toString() == txt[i + 1].toString()) {
                couple = listOf<String>(txt[i].toString(), "z").toTypedArray()
                i--
            } else {
                couple = listOf<String>(txt[i].toString(), txt[i + 1].toString()).toTypedArray()
            }
            coordonnees = find(tab, couple)
            var ncoo: Array<String> = sommets(tab, coordonnees)
            sb.append(tab[valueOf(ncoo[0])][valueOf(ncoo[1])])
            sb.append(tab[valueOf(ncoo[2])][valueOf(ncoo[3])])
            i += 2
        } while (i < txt.length)
        return sb.toString()
    }

    private fun sommets(tab: Array<Array<String>>, coordonnees: Array<String>): Array<String> {
        var coo: Array<String> = coordonnees
        println(
            "<-----------" + tab[valueOf(coo[0])][valueOf(coo[1])] + "-" + tab[valueOf(coo[2])][valueOf(
                coo[3]
            )]
        )
        if (coordonnees[0] == coordonnees[2]) {
            println("same col")
            var i: Int = valueOf(coordonnees[1])
            var j: Int = valueOf(coordonnees[3])
            if (i == 4) i = 0 else i++
            if (j == 4) j = 0 else j++
            coo[1] = i.toString()
            coo[3] = j.toString()
            println(
                "----------->" + tab[valueOf(coo[0])][valueOf(coo[1])] + "-" + tab[valueOf(coo[2])][valueOf(
                    coo[3]
                )]
            )
            return coo
        } else if (coordonnees[1] == coordonnees[3]) {
            println("same line")
            var i: Int = valueOf(coordonnees[0])
            var j: Int = valueOf(coordonnees[2])
            if (i == 4) i = 0 else i++
            if (j == 4) j = 0 else j++
            coo[0] = i.toString()
            coo[2] = j.toString()
            println(
                "----------->" + tab[valueOf(coo[0])][valueOf(coo[1])] + "-" + tab[valueOf(coo[2])][valueOf(
                    coo[3]
                )]
            )
            return coo
        } else {
            println("sommets")
            var save = coordonnees[0]
            coo[0] = coordonnees[2]
            coo[2] = save
            println(
                "----------->" + tab[valueOf(coo[0])][valueOf(coo[1])] + "-" + tab[valueOf(coo[2])][valueOf(
                    coo[3]
                )]
            )
            return coo
        }
    }


    private fun find(tab: Array<Array<String>>, couple: Array<String>): Array<String> {
        var coo: Array<String> = Array(4) { "" }
        var i = 0
        var j = 0
        var y = 0
        var x = tab[i].size
        for (k in 0 until 2) {
            println("----------------------searching : " + couple[k])
            if (couple[k] == "w") couple[k] = "v"
            while (tab[i][j] != couple[k].toLowerCase()) {
                println("i = $i j=$j -> ${tab[i][j]} != ${couple[k]} ")
                j++
                if (j == 5) {
                    i++
                    j = 0
                }
            }
            coo[y] = i.toString()
            y++
            coo[y] = j.toString()
            y++
            i = 0
            j = 0
        }

        return coo
    }

    private fun dechiffrement(tab: Array<Array<String>>, txt: String): String {
        var coordonnees: Array<String>
        var couple: Array<String>
        var sb: StringBuilder = StringBuilder()
        var i = 0
        do {
            if (i == (txt.length - 1)) {
                couple = listOf<String>(txt[i].toString(), "z").toTypedArray()
            } else if (txt[i].toString() == txt[i + 1].toString()) {
                couple = listOf<String>(txt[i].toString(), "z").toTypedArray()
                i--
            } else {
                couple = listOf<String>(txt[i].toString(), txt[i + 1].toString()).toTypedArray()
            }
            coordonnees = find(tab, couple)
            var ncoo: Array<String> = antisommets(tab, coordonnees)
            sb.append(tab[valueOf(ncoo[0])][valueOf(ncoo[1])])
            sb.append(tab[valueOf(ncoo[2])][valueOf(ncoo[3])])
            if (i >= 1) {
                println("--" + sb[i - 1].toString() + "---" + sb[i].toString() + "****" + sb[i - 2].toString())
                if (sb[i - 1].toString() == "z" && sb[i].toString() == sb[i - 2].toString()) {
                    sb.deleteCharAt(i - 1)
                }
            }
            i += 2
        } while (i < txt.length)
        return sb.toString()
    }

    private fun antisommets(tab: Array<Array<String>>, coordonnees: Array<String>): Array<String> {
        var coo: Array<String> = coordonnees
        //println("<-----------"+tab[valueOf(coo[0])][valueOf(coo[1])]+"-"+tab[valueOf(coo[2])][valueOf(coo[3])])
        if (coordonnees[0] == coordonnees[2]) {
            //println("same col")
            var i: Int = valueOf(coordonnees[1])
            var j: Int = valueOf(coordonnees[3])
            if (i == 0) i = 4 else i--
            if (j == 0) j = 4 else j--
            coo[1] = i.toString()
            coo[3] = j.toString()
            //println("----------->"+tab[valueOf(coo[0])][valueOf(coo[1])]+"-"+tab[valueOf(coo[2])][valueOf(coo[3])])
            return coo
        } else if (coordonnees[1] == coordonnees[3]) {
            //println("same line")
            var i: Int = valueOf(coordonnees[0])
            var j: Int = valueOf(coordonnees[2])
            if (i == 0) i = 4 else i--
            if (j == 0) j = 4 else j--
            coo[0] = i.toString()
            coo[2] = j.toString()
            //println("----------->"+tab[valueOf(coo[0])][valueOf(coo[1])]+"-"+tab[valueOf(coo[2])][valueOf(coo[3])])
            return coo
        } else {
            //println("sommets")
            var save = coordonnees[0]
            coo[0] = coordonnees[2]
            coo[2] = save
            //println("----------->"+tab[valueOf(coo[0])][valueOf(coo[1])]+"-"+tab[valueOf(coo[2])][valueOf(coo[3])])
            return coo
        }
    }


}


