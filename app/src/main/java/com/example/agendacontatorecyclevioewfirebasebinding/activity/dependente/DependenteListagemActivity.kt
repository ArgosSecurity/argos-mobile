package com.example.agendacontatorecyclevioewfirebasebinding.activity.dependente

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agendacontatorecyclevioewfirebasebinding.activity.dependente.DependenteFormularioActivity
import com.example.agendacontatorecyclevioewfirebasebinding.databinding.ListagemDependenteLayoutBinding
import com.example.agendacontatorecyclevioewfirebasebinding.model.Dependente
import com.example.agendacontatorecyclevioewfirebasebinding.recycle.dependente.DependenteAdapter
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
    val lista = ArrayList<Dependente>()
    private var clientHttp = OkHttpClient()
    lateinit var rcvContatos : RecyclerView
    lateinit var binding : ListagemDependenteLayoutBinding
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = ListagemDependenteLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.listagem_layout)
        // rcvContatos = findViewById(R.id.rcvContatos)
        val adapter = DependenteAdapter(this, lista)
        binding.apply {
            rcvContatos.adapter = adapter
            rcvContatos.layoutManager = LinearLayoutManager(
                    this@DependenteListagemActivity)
            // val btnFormulario = findViewById<Button>(R.id.btnFormulario)
            btnFormulario.setOnClickListener {
                val intent = Intent(this@DependenteListagemActivity,
                    DependenteFormularioActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val request = Request.Builder()
            .get()
            .url("https://fatec-mobile-default-rtdb.firebaseio.com/dependente.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("AGENDA-DEPENDENTE", e?.message.toString())
            }
            override fun onResponse(call: Call?, response: Response?) {
                Log.i("AGENDA-DEPENDENTE", "Dados recebidos convertendo dependente")
                val body = response?.body()
                val type = object : TypeToken<HashMap<String?, Dependente?>?>() {}.type
                val myMap: HashMap<String, Dependente> = gson.fromJson(body?.string(), type)
                val listaTemp = ArrayList<Dependente>()
                myMap.keys.forEach {
                    val contato = myMap[it]
                    if (contato != null) {
                        contato.id = it
                        Log.i("AGENDA-DEPENDENTE", "DEPENDENTE: $contato")
                        listaTemp.add(contato)
                    }
                }
                this@DependenteListagemActivity.runOnUiThread {
                    lista.clear()
                    lista.addAll(listaTemp)
                    binding.rcvContatos.adapter?.notifyDataSetChanged()
                }
            }
        }
        clientHttp.newCall(request).enqueue(response)
    }
}