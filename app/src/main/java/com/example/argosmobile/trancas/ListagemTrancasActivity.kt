package com.example.myapp.activity

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.argosmobile.R
import com.example.argosmobile.model.Tranca
import com.example.myapp.recycle.TrancaAdapter
import com.example.argosmobile.util.ApiUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

class ListagemTrancasActivity : AppCompatActivity() {

    private lateinit var recyclerViewTrancas: RecyclerView
    private lateinit var btnNovaTranca: Button
    private val trancas = ArrayList<Tranca>()
    private lateinit var adapter: TrancaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem_trancas)

        recyclerViewTrancas = findViewById(R.id.recyclerViewTrancas)
        btnNovaTranca = findViewById(R.id.btnNovaTranca)

        adapter = TrancaAdapter(this, trancas)
        recyclerViewTrancas.adapter = adapter
        recyclerViewTrancas.layoutManager = LinearLayoutManager(this)

        btnNovaTranca.setOnClickListener {
            // Inicie a activity de formulário
            startActivity(FormularioTrancaActivity.createIntent(this))
        }

        // Carregar trancas da API
        fetchTrancas()
    }

    private fun fetchTrancas() {
        val request = Request.Builder()
            .url(ApiUtil.BASE_URL + "/trancas")
            .build()

        ApiUtil.clientHttp.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(this@ListagemTrancasActivity, "Erro ao carregar trancas", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val json = response.body?.string()
                    val listType = object : TypeToken<List<Tranca>>() {}.type
                    trancas.clear()
                    trancas.addAll(Gson().fromJson(json, listType))
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@ListagemTrancasActivity, "Erro ao carregar trancas", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}