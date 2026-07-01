package com.example.tp1

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var poidsInput: EditText
    private lateinit var tailleInput: EditText
    private lateinit var resultatText: TextView
    private lateinit var categorieText: TextView
    private lateinit var calculerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        poidsInput = findViewById(R.id.poidsInput)
        tailleInput = findViewById(R.id.tailleInput)
        resultatText = findViewById(R.id.resultatText)
        categorieText = findViewById(R.id.categorieText)
        calculerButton = findViewById(R.id.calculerButton)

        calculerButton.setOnClickListener {
            calculerIMC()
        }
    }

    private fun calculerIMC() {

        val poids = poidsInput.text.toString().toDoubleOrNull() ?: 0.0
        val taille = tailleInput.text.toString().toDoubleOrNull() ?: 0.0

        if (poids <= 0 || taille <= 0) {
            Toast.makeText(
                this,
                "Les valeurs doivent être positives",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val imc = poids / (taille * taille)

        resultatText.text = String.format(Locale.FRANCE, "IMC = %.2f", imc)

        when {
            imc < 18.5 -> afficherCategorie("Maigreur", "#3498DB")
            imc < 25 -> afficherCategorie("Corpulence normale", "#27AE60")
            imc < 30 -> afficherCategorie("Surpoids", "#F1C40F")
            imc < 35 -> afficherCategorie("Obésité modérée", "#E67E22")
            else -> afficherCategorie("Obésité sévère", "#E74C3C")
        }
    }

    private fun afficherCategorie(message: String, couleur: String) {
        categorieText.text = message
        categorieText.setBackgroundColor(Color.parseColor(couleur))
    }
}