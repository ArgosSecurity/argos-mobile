package com.example.agendacontatorecyclevioewfirebasebinding.activity.chamados

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agendacontatorecyclevioewfirebasebinding.databinding.ListagemChamadoLayoutBinding
import com.example.agendacontatorecyclevioewfirebasebinding.model.Chamado
import com.example.agendacontatorecyclevioewfirebasebinding.recycle.chamado.ChamadoAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class ChamadoListagemActivity: AppCompatActivity() {
    val gson = Gson()
    val lista = ArrayList<Chamado>()
    private var clientHttp = OkHttpClient()
    lateinit var rcvContatos : RecyclerView
    lateinit var binding : ListagemChamadoLayoutBinding
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = ListagemChamadoLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.listagem_layout)
        // rcvContatos = findViewById(R.id.rcvContatos)
        val adapter = ChamadoAdapter(this, lista)
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
            .url("https://fatec-mobile-default-rtdb.firebaseio.com/chamado.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("AGENDA-CONTATO", e?.message.toString())
            }
            override fun onResponse(call: Call?, response: Response?) {
                Log.i("AGENDA-CONTATO", "Dados recebidos convertendo chamado")
                val body = response?.body()
                val type = object : TypeToken<HashMap<String?, Chamado?>?>() {}.type
                val myMap: HashMap<String, Chamado> = gson.fromJson(body?.string(), type)
                val listaTemp = ArrayList<Chamado>()
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