package com.example.des_ctryptage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import java.lang.Integer.valueOf
import kotlin.text.StringBuilder

class Delastelle : AppCompatActivity() {

    //on appel une instance de polybe car ce surchiffrement utilise beaucoup polybe
    val c = Polybe()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delastelle)


        //on oublie pas de remplire le carre 6x6 et on l'affiche pour verifié que c'est bien lui
        c.fill()
        affiche(c.carre, 6, 6)
        val btdo: Button = findViewById(R.id.delbt)
        val txtin: EditText = findViewById(R.id.delin)
        val key: EditText = findViewById((R.id.delkey))
        val txtout: EditText = findViewById(R.id.delout)
        val btundo: Button = findViewById(R.id.delbtun)
        val btrand: Button = findViewById(R.id.delrand)

        btrand.setOnClickListener {
            //on randomise le carre pour plus de sécurité , a noter que si l'app se ferme il sera
            // EXTREMEMENT compliquer d'obtenir le même carre
            c.random()
            affiche(c.carre, 6, 6)
        }
        btdo.setOnClickListener {
            val out: String = chiffrement(txtin.text.toString(), key.text.toString())
            txtout.setText(out)
        }
        btundo.setOnClickListener {
            val out: String = dechiffrement(txtout.text.toString(), key.text.toString())
            txtin.setText(out)
        }
    }

    //on par du texte chiffrer on fais le chemin inverse du chiffrement
    private fun dechiffrement(txt: String, key: String): String {

        val tab1: Array<String> = Array(txt.length) { " " }
        val tab2: Array<String> = Array(txt.length) { " " }

        //on effectue le chiffrement pour avoir la liste de coordonnées
        val prestri = c.chiffrement(txt)
        val nbdiv = txt.length / valueOf(key)
        val nbkey = valueOf(key)
        var i = 0
        var k = 0

        //on serpare en deux tableau en coupant le tous en 2*key pour récuperer les coordonnees
        //des elements du texte en clair
        for (div in 0 until nbdiv) {
            for (j in nbkey * 2 * div until nbkey * 2 * div + nbkey) {
                tab1[i] = prestri[j].toString()
                i++
            }
            for (j in nbkey * 2 * div + nbkey until nbkey * 2 * div + 2 * nbkey) {
                tab2[k] = prestri[j].toString()
                k++
            }
        }

        //on remet tous dans un stringbuilder qui aura les coordonnees des elements du texte
        //dans le bonne ordre pour utiliser le déchiffrement de polybe
        val subsb: java.lang.StringBuilder = java.lang.StringBuilder()
        for (c in tab1.indices) {
            subsb.append(tab1[c])
            subsb.append(tab2[c])
        }


        return c.dechiffrement(subsb.toString())
    }


    private fun chiffrement(txt: String, key: String): String {
        val sb: StringBuilder = StringBuilder()
        val subsb: StringBuilder = StringBuilder()
        val totab: StringBuilder = StringBuilder()
        val kei = valueOf(key)
        totab.append(txt)

        //on vas tous d'abord voir en combient de partie notre texte en claire vas être séparer
        // en fonction de la clef et on ajoute des 'x' pour remplire la dernière partie
        val nbdiv: Int = if (totab.length % kei != 0) {
            (totab.length / kei) + 1
        } else {
            totab.length / kei
        }
        while (totab.length != (kei * nbdiv)) {
            totab.append("x")
        }

        //on fais le chiffrement de polybe et on prepare deux tableau pour stocker les coordonnes
        val tab = c.chiffrement(totab.toString())//string
        val tab1: Array<String> = Array(tab.length / 2) { " " }
        val tab2: Array<String> = Array(tab.length / 2) { " " }

        //on remplis les deux tableaux avec les coordonnees tab1 aura les ligne et tab 2 les
        // colonnes de chaque elements
        var k = 0
        for (i in tab.indices step 2) {
            tab1[k] = tab[i].toString()
            println("***[${tab1[k]}]****")
            tab2[k] = tab[i + 1].toString()
            k++
        }

        //en fonction de key on vas remplire un stringbuilder et créer de nouvelles coordonnees
        for (div in 0 until nbdiv) {
            var i = 0
            do {
                subsb.append(tab1[(div * kei) + i])//sa a garde k !
                i++
            } while (i != kei)
            i = 0
            do {
                subsb.append(tab2[(div * kei) + i])
                i++
            } while (i != kei)
        }
        //puis on fais le déchiffrement pour avoir notre texte chiffre
        sb.append(c.dechiffrement(subsb.toString()))

        return sb.toString()
    }

    //sert a afficher les carre de polybe
    fun affiche(tabi: Array<Array<String>>, col: Int, line: Int) {

        val tableLayout: TableLayout = findViewById(R.id.deltable)
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