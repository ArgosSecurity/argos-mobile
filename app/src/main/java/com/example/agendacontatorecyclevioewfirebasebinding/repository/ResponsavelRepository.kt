package com.example.agendacontatorecyclevioewfirebasebinding.repository

import android.util.Log
import com.example.agendacontatorecyclevioewfirebasebinding.model.Responsavel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ResponsavelRepository {
    private var clientHttp = OkHttpClient()
    fun apagarResponsavel(responsavel : Responsavel) {
        val request = Request.Builder()
            .delete()
            .url("https://fatec-mobile-default-rtdb.firebaseio.com/responsavel/${responsavel.id}.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("AGENDA-RESPONSAVEL", e?.message.toString())
            }
            override fun onResponse(call: Call?, response: Response?) {
                Log.e("AGENDA-RESPONSAVEL", "Responsavel apagado com sucesso")
            }
        }
        clientHttp.newCall(request).enqueue(response)
    }
}