package com.example.agendacontatorecyclevioewfirebasebinding.repository

import android.util.Log
import com.example.agendacontatorecyclevioewfirebasebinding.model.Dependente
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class DependenteRepository {
    private var clientHttp = OkHttpClient()
    fun apagarDependente(dependente : Dependente) {
        val request = Request.Builder()
            .delete()
            .url("https://fatec-mobile-default-rtdb.firebaseio.com/dependente/${dependente.id}.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("AGENDA-CONTATO", e?.message.toString())
            }
            override fun onResponse(call: Call?, response: Response?) {
                Log.e("AGENDA-CONTATO", "Dependente apagado com sucesso")
            }
        }
        clientHttp.newCall(request).enqueue(response)
    }
}