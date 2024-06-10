package com.example.agendacontatorecyclevioewfirebasebinding.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.agendacontatorecyclevioewfirebasebinding.R
import com.example.agendacontatorecyclevioewfirebasebinding.activity.responsavel.ChamadoFormularioActivity
import com.example.agendacontatorecyclevioewfirebasebinding.activity.responsavel.DependenteFormularioActivity
import com.example.agendacontatorecyclevioewfirebasebinding.activity.responsavel.TrancaFormularioActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnResponsaveis = findViewById<Button>(R.id.btnResponsaveis)
        val btnDependentes = findViewById<Button>(R.id.btnDependentes)
        val btnTrancas = findViewById<Button>(R.id.btnTrancas)
        val btnChamados = findViewById<Button>(R.id.btnChamados)

        btnResponsaveis.setOnClickListener {
            startActivity(Intent(this, ChamadoFormularioActivity::class.java))
        }

        btnDependentes.setOnClickListener {
            startActivity(Intent(this, DependenteFormularioActivity::class.java))
        }

        btnTrancas.setOnClickListener {
            startActivity(Intent(this, TrancaFormularioActivity::class.java)) // Abre a listagem de trancas
        }

        btnChamados.setOnClickListener {
            startActivity(Intent(this, ChamadoFormularioActivity::class.java))
        }
    }
}