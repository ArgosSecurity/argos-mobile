package com.example.agendacontatorecyclevioewfirebasebinding.repository

import android.util.Log
import com.example.agendacontatorecyclevioewfirebasebinding.model.Tranca
import okhttp3.*
import java.io.IOException

class TrancaRepository {
    private var clientHttp = OkHttpClient()

    fun apagarTranca(tranca: Tranca) {
        val request = Request.Builder()
            .delete()
            .url("https://fatec-mobile-default-rtdb.firebaseio.com/tranca/${tranca.id}.json")
            .build()

        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("AGENDA-TRANCA", e?.message.toString())
            }

            override fun onResponse(call: Call?, response: Response?) {
                Log.e("AGENDA-TRANCA", "Tranca apagada com sucesso")
            }
        }
        clientHttp.newCall(request).enqueue(response)
    }
}