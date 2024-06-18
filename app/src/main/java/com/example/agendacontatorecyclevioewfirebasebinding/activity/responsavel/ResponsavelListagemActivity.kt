package com.example.agendacontatorecyclevioewfirebasebinding.activity.responsavel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agendacontatorecyclevioewfirebasebinding.databinding.ListagemResponsavelLayoutBinding
import com.example.agendacontatorecyclevioewfirebasebinding.model.Responsavel
import com.example.agendacontatorecyclevioewfirebasebinding.recycle.responsavel.ResponsavelAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

class ResponsavelListagemActivity : AppCompatActivity() {
    val gson = Gson()
    val lista = ArrayList<Responsavel>()
    private var clientHttp = OkHttpClient()
    lateinit var rcvContatos: RecyclerView
    lateinit var binding: ListagemResponsavelLayoutBinding

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = ListagemResponsavelLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ResponsavelAdapter(this, lista)
        binding.apply {
            rcvContatos.adapter = adapter
            rcvContatos.layoutManager = LinearLayoutManager(
                this@ResponsavelListagemActivity
            )

            btnFormulario.setOnClickListener {
                val intent = Intent(this@ResponsavelListagemActivity,
                    ResponsavelFormularioActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val request = Request.Builder()
            .get()
            .url("https://fatec-mobile-default-rtdb.firebaseio.com/responsavel.json")
            .build()

        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("AGENDA-RESPONSAVEL", e?.message.toString())
            }

            override fun onResponse(call: Call?, response: Response?) {
                Log.i("AGENDA-RESPONSAVEL", "Dados recebidos convertendo responsavel")
                val body = response?.body()
                val type = object : TypeToken<HashMap<String?, Responsavel?>?>() {}.type
                val myMap: HashMap<String, Responsavel> = gson.fromJson(body?.string(), type)
                val listaTemp = ArrayList<Responsavel>()

                myMap.keys.forEach {
                    val responsavel = myMap[it]
                    if (responsavel != null) {
                        responsavel.id = it
                        Log.i("AGENDA-RESPONSAVEL", "Responsável: $responsavel")
                        listaTemp.add(responsavel)
                    }
                }

                this@ResponsavelListagemActivity.runOnUiThread {
                    lista.clear()
                    lista.addAll(listaTemp)
                    binding.rcvContatos.adapter?.notifyDataSetChanged()
                }
            }
        }
        clientHttp.newCall(request).enqueue(response)
    }
}