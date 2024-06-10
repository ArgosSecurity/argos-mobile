package com.example.agendacontatorecyclevioewfirebasebinding.activity.responsavel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agendacontatorecyclevioewfirebasebinding.databinding.ListagemLayoutBinding
import com.example.agendacontatorecyclevioewfirebasebinding.model.Contato
import com.example.agendacontatorecyclevioewfirebasebinding.recycle.ContatoAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class DependenteListagemActivity: AppCompatActivity() {
    val gson = Gson()
    val lista = ArrayList<Contato>()
    private var clientHttp = OkHttpClient()
    lateinit var rcvContatos : RecyclerView
    lateinit var binding : ListagemLayoutBinding
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = ListagemLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.listagem_layout)
        // rcvContatos = findViewById(R.id.rcvContatos)
        val adapter = ContatoAdapter(this, lista)
        binding.apply {
            rcvContatos.adapter = adapter
            rcvContatos.layoutManager = LinearLayoutManager(
                    this@ChamadoListagemActivity)
            // val btnFormulario = findViewById<Button>(R.id.btnFormulario)
            btnFormulario.setOnClickListener {
                val intent = Intent(this@ChamadoListagemActivity,
                    ChamadoFormularioActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val request = Request.Builder()
            .get()
            .url("https://fatec-2024-1s-pdmi-default-rtdb.firebaseio.com/agenda.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("AGENDA-CONTATO", e?.message.toString())
            }
            override fun onResponse(call: Call?, response: Response?) {
                Log.i("AGENDA-CONTATO", "Dados recebidos convertendo")
                val body = response?.body()
                val type = object : TypeToken<HashMap<String?, Contato?>?>() {}.type
                val myMap: HashMap<String, Contato> = gson.fromJson(body?.string(), type)
                val listaTemp = ArrayList<Contato>()
                myMap.keys.forEach {
                    val contato = myMap[it]
                    if (contato != null) {
                        contato.id = it
                        Log.i("AGENDA-CONTATO", "Contato: $contato")
                        listaTemp.add(contato)
                    }
                }
                this@ChamadoListagemActivity.runOnUiThread {
                    lista.clear()
                    lista.addAll(listaTemp)
                    binding.rcvContatos.adapter?.notifyDataSetChanged()
                }
            }
        }
        clientHttp.newCall(request).enqueue(response)
    }
}