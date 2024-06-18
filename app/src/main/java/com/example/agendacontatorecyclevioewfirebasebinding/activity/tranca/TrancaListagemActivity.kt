package com.example.agendacontatorecyclevioewfirebasebinding.activity.tranca

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agendacontatorecyclevioewfirebasebinding.databinding.ListagemTrancaLayoutBinding
import com.example.agendacontatorecyclevioewfirebasebinding.model.Tranca
import com.example.agendacontatorecyclevioewfirebasebinding.recycle.tranca.TrancaAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

class TrancaListagemActivity : AppCompatActivity() {
    val gson = Gson()
    val lista = ArrayList<Tranca>()
    private var clientHttp = OkHttpClient()
    lateinit var rcvContatos: RecyclerView
    lateinit var binding: ListagemTrancaLayoutBinding

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = ListagemTrancaLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TrancaAdapter(this, lista)
        binding.apply {
            rcvContatos.adapter = adapter
            rcvContatos.layoutManager = LinearLayoutManager(
                this@TrancaListagemActivity
            )

            btnFormulario.setOnClickListener {
                val intent = Intent(this@TrancaListagemActivity,
                    TrancaFormularioActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val request = Request.Builder()
            .get()
            .url("https://fatec-mobile-default-rtdb.firebaseio.com/tranca.json")
            .build()

        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("AGENDA-TRANCA", e?.message.toString())
            }

            override fun onResponse(call: Call?, response: Response?) {
                Log.i("AGENDA-TRANCA", "Dados recebidos convertendo tranca")
                val body = response?.body()
                val type = object : TypeToken<HashMap<String?, Tranca?>?>() {}.type
                val myMap: HashMap<String, Tranca> = gson.fromJson(body?.string(), type)
                val listaTemp = ArrayList<Tranca>()

                myMap.keys.forEach {
                    val tranca = myMap[it]
                    if (tranca != null) {
                        tranca.id = it
                        Log.i("AGENDA-TRANCA", "Tranca: $tranca")
                        listaTemp.add(tranca)
                    }
                }

                this@TrancaListagemActivity.runOnUiThread {
                    lista.clear()
                    lista.addAll(listaTemp)
                    binding.rcvContatos.adapter?.notifyDataSetChanged()
                }
            }
        }
        clientHttp.newCall(request).enqueue(response)
    }
}