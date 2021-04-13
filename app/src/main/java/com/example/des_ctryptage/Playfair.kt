package com.example.des_ctryptage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import java.lang.Integer.valueOf
import java.lang.StringBuilder
import java.util.*

class Playfair : AppCompatActivity() {

    lateinit var tableLayout: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playfair)

        tableLayout = findViewById(R.id.playtable)

        val btplfr: Button = findViewById(R.id.playfrbt)
        val keytxt: EditText = findViewById(R.id.playfrkey)
        val txtin: EditText = findViewById(R.id.playin)
        val txtout: EditText = findViewById(R.id.playout)
        val btundo: Button = findViewById(R.id.playfrbtun)


        btplfr.setOnClickListener {
            tableLayout.removeAllViews()
            val tab = dotab(keytxt.text.toString())
            val texto = chiffrement(tab, txtin.text.toString())
            txtout.setText(texto)
        }
        btundo.setOnClickListener {
            tableLayout.removeAllViews()
            val tab = dotab(keytxt.text.toString())
            val texto = dechiffrement(tab, txtout.text.toString())
            txtin.setText(texto)
        }
    }

    //on vas faire un premier tableau qui sera celui a partir de la clef
    fun dotab(key: String): Array<Array<String>> {
        val tab: Array<Array<String>>
        val nbcol = key.length
        var nbl = (36 / nbcol)
        if (nbcol + nbl != 36) {
            nbl++
        }
        var o = 0
        var n = 0
        tab = Array(nbcol) { Array(nbl) { " " } }
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
        return maketab(tab, nbl)

    }

//pour faire un petit espace entre les deux tableaux
    private fun afficheblanc() {

        var cell: TextView
        val row = TableRow(this)
        for (c in 0 until 6) {
            cell = TextView(this)
            cell.text = "||"
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

    //permet d'afficher les tableaux
    private fun affiche(tabi: Array<Array<String>>, col: Int, line: Int) {

        val tableLayout: TableLayout = findViewById(R.id.playtable)
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
                TableRow.LayoutParams(
                    0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                    1F
                ).also { cell.layoutParams = it }
                row.addView(cell)
            }
            tableLayout.addView(row)
        }
    }

    //on fais le tableau final
    private fun maketab(tab: Array<Array<String>>, line: Int): Array<Array<String>> {
        val finaltab: Array<Array<String>> = Array(6) { Array(6) { " " } }
        var i = 0
        var j = 0
        for (l in 0 until 6) {
            for (c in 0 until 6) {
                if (tab[i][j] != "_") {

                    finaltab[c][l] = tab[i][j]
                    j++
                    if (j == line) {
                        j = 0
                        i++

                    }
                } else {
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


    //on va creer les deux tableau et utiliser le dernier pour chiffrer
    private fun chiffrement(tab: Array<Array<String>>, txt: String): String {
        var coordonnees: Array<String>
        var couple: Array<String>
        val sb = StringBuilder()
        var i = 0
        //on serape en couple de 2 si le dernière element est seul on rajoute un 'z'
        //si deux lettre identique se suivent on intercale un 'z' entre
        do {
            when {
                i == (txt.length - 1) -> {
                    couple = listOf(txt[i].toString(), "z").toTypedArray()
                }
                txt[i].toString() == txt[i + 1].toString() -> {
                    couple = listOf(txt[i].toString(), "z").toTypedArray()
                    i--
                }
                else -> {
                    couple = listOf(txt[i].toString(), txt[i + 1].toString()).toTypedArray()
                }
            }
            //on récupère les coordonnees des elements du couple
            coordonnees = find(tab, couple)
            //on prends les nouvelles coordonnees et on ajoute au stringbuilder
            val ncoo: Array<String> = sommets(coordonnees)
            sb.append(tab[valueOf(ncoo[0])][valueOf(ncoo[1])])
            sb.append(tab[valueOf(ncoo[2])][valueOf(ncoo[3])])
            i += 2
        } while (i < txt.length)
        return sb.toString()
    }

    private fun sommets( coordonnees: Array<String>): Array<String> {
        val coo: Array<String> = coordonnees

        when {
            coordonnees[0] == coordonnees[2] -> {
                //meme colone on décale d'un vers le bas
                var i: Int = valueOf(coordonnees[1])
                var j: Int = valueOf(coordonnees[3])
                if (i == 4) i = 0 else i++
                if (j == 4) j = 0 else j++
                coo[1] = i.toString()
                coo[3] = j.toString()
                return coo
            }
            coordonnees[1] == coordonnees[3] -> {
                //meme ligne
                var i: Int = valueOf(coordonnees[0])
                var j: Int = valueOf(coordonnees[2])
                if (i == 4) i = 0 else i++
                if (j == 4) j = 0 else j++
                coo[0] = i.toString()
                coo[2] = j.toString()
                //on decale d'un vers la droite
                return coo
            }
            else -> {
                //on fais normalement
                val save = coordonnees[0]
                coo[0] = coordonnees[2]
                coo[2] = save
                return coo
            }
        }
    }


    //on parcour le tableau jusqu'a touver nos elements
    private fun find(tab: Array<Array<String>>, couple: Array<String>): Array<String> {
        val coo: Array<String> = Array(4) { "" }
        var i = 0
        var j = 0
        var y = 0
        for (k in 0 until 2) {
            if (couple[k] == "w") couple[k] = "v"
            while (tab[i][j] != couple[k].toLowerCase(Locale.ROOT)) {
                j++
                if (j == 6) {
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

    //meme principe que chiffrement
    private fun dechiffrement(tab: Array<Array<String>>, txt: String): String {
        var coordonnees: Array<String>
        var couple: Array<String>
        val sb = StringBuilder()
        var i = 0
        do {
            when {
                i == (txt.length - 1) -> {
                    couple = listOf(txt[i].toString(), "z").toTypedArray()
                }
                txt[i].toString() == txt[i + 1].toString() -> {
                    couple = listOf(txt[i].toString(), "z").toTypedArray()
                    i--
                }
                else -> {
                    couple = listOf(txt[i].toString(), txt[i + 1].toString()).toTypedArray()
                }
            }
            coordonnees = find(tab, couple)
            val ncoo: Array<String> = antisommets(coordonnees)
            sb.append(tab[valueOf(ncoo[0])][valueOf(ncoo[1])])
            sb.append(tab[valueOf(ncoo[2])][valueOf(ncoo[3])])
            if (i >= 1) {
                if (sb[i - 1].toString() == "z" && sb[i].toString() == sb[i - 2].toString()) {
                    sb.deleteCharAt(i - 1)
                }
            }
            i += 2
        } while (i < txt.length)
        return sb.toString()
    }

    //exactement l'inverse de sommets
    private fun antisommets( coordonnees: Array<String>): Array<String> {
        val coo: Array<String> = coordonnees
        when {
            coordonnees[0] == coordonnees[2] -> {
                var i: Int = valueOf(coordonnees[1])
                var j: Int = valueOf(coordonnees[3])
                if (i == 0) i = 4 else i--
                if (j == 0) j = 4 else j--
                coo[1] = i.toString()
                coo[3] = j.toString()
                return coo
            }
            coordonnees[1] == coordonnees[3] -> {
                var i: Int = valueOf(coordonnees[0])
                var j: Int = valueOf(coordonnees[2])
                if (i == 0) i = 4 else i--
                if (j == 0) j = 4 else j--
                coo[0] = i.toString()
                coo[2] = j.toString()
                return coo
            }
            else -> {
                val save = coordonnees[0]
                coo[0] = coordonnees[2]
                coo[2] = save
                return coo
            }
        }
    }


}


