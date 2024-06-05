package com.example.myapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.argosmobile.R
import com.example.myapp.activity.ListagemTrancasActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTrancas = findViewById<Button>(R.id.btnTrancas)
        btnTrancas.setOnClickListener {
            startActivity(Intent(this, ListagemTrancasActivity::class.java))
        }

        // Adicione botões para outros formulários aqui
    }
}