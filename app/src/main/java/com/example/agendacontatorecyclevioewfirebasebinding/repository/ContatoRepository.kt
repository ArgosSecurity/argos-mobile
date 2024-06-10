package com.example.agendacontatorecyclevioewfirebasebinding.repository

import android.util.Log
import com.example.agendacontatorecyclevioewfirebasebinding.model.Contato
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ContatoRepository {
    private var clientHttp = OkHttpClient()
    fun apagarContato(contato : Contato) {
        val request = Request.Builder()
            .delete()
            .url("https://fatec-2024-1s-pdmi-default-rtdb.firebaseio.com/agenda/${contato.id}.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("AGENDA-CONTATO", e?.message.toString())
            }
            override fun onResponse(call: Call?, response: Response?) {
                Log.e("AGENDA-CONTATO", "Contato apagado com sucesso")
            }
        }
        clientHttp.newCall(request).enqueue(response)
    }
}