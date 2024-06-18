package com.example.agendacontatorecyclevioewfirebasebinding.activity.tranca

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.agendacontatorecyclevioewfirebasebinding.R
import com.example.agendacontatorecyclevioewfirebasebinding.activity.tranca.TrancaListagemActivity
import com.example.agendacontatorecyclevioewfirebasebinding.databinding.FormularioTrancaLayoutBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class TrancaFormularioActivity : AppCompatActivity() {

    private var clientHttp = OkHttpClient()
    private lateinit var binding : FormularioTrancaLayoutBinding

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = FormularioTrancaLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // setContentView(R.layout.formulario_layout)
        // val btnGravar = findViewById<Button>(R.id.btnGravar)
        // val btnListagem = findViewById<Button>(R.id.btnListagem)
        binding.btnListagem.setOnClickListener {
            val intent = Intent(this, TrancaListagemActivity::class.java)
            startActivity(intent)
        }

        binding.btnSalvar.setOnClickListener {
            val txtLocalidade = findViewById<EditText>(R.id.edtLocalidade)
            val txtIdRegistro = findViewById<EditText>(R.id.edtIdRegistroFisico)

            val body = RequestBody.create(
                MediaType.parse("application/json"),
                """
                    {
                        "localidade": "${txtLocalidade.text}",
                        "idRegistro": "${txtIdRegistro.text}"
                    }
                """.trimIndent()
            )

            val request = Request.Builder()
                .post(body)
                .url("https://fatec-mobile-default-rtdb.firebaseio.com/tranca.json")
                .build()
            val response = object : Callback {
                override fun onFailure(call: Call?, e: IOException?) {
                    Log.e("AGENDA-CONTATO", e?.message.toString())
                }

                override fun onResponse(call: Call?, response: Response?) {
                    val localBody = response?.body()
                    localBody?.charStream()?.buffered()?.lines()?.forEach {
                        Log.i("AGENDA-CONTATO", it.toString())
                    }

                }
            }
            clientHttp.newCall(request).enqueue(response)
        }
    }
}