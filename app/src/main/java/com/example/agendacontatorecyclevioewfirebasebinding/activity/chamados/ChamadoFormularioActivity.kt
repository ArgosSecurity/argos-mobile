package com.example.agendacontatorecyclevioewfirebasebinding.activity.chamados

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.agendacontatorecyclevioewfirebasebinding.R
import com.example.agendacontatorecyclevioewfirebasebinding.databinding.FormularioChamadoLayoutBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class ChamadoFormularioActivity : AppCompatActivity() {

    private var clientHttp = OkHttpClient()
    private lateinit var binding : FormularioChamadoLayoutBinding

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = FormularioChamadoLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // setContentView(R.layout.formulario_layout)
        // val btnGravar = findViewById<Button>(R.id.btnGravar)
        // val btnListagem = findViewById<Button>(R.id.btnListagem)
        binding.btnListagem.setOnClickListener {
            val intent = Intent(this, ChamadoListagemActivity::class.java)
            startActivity(intent)
        }

        binding.btnGravar.setOnClickListener {
            val txtTipoChamado = findViewById<EditText>(R.id.txtChamadoTipoChamado)
            val txtNumeroRegistroTag = findViewById<EditText>(R.id.txtChamadoNumeroRegistroTag)
            val txtTipoTag = findViewById<EditText>(R.id.txtChamadoTipoTag)
            val txtHorarioEntrada = findViewById<EditText>(R.id.txtChamadoHorarioEntrada)
            val txtHorarioSaida = findViewById<EditText>(R.id.txtChamadoHorarioSaida)

            val body = RequestBody.create(
                MediaType.parse("application/json"),
                """
                    {
                        "tipoChamado": "${txtTipoChamado.text}",
                        "numeroRegistroTag": "${txtNumeroRegistroTag.text}",
                        "tipoTag": "${txtTipoTag.text}",
                        "horarioEntrada": "${txtHorarioEntrada.text}",
                        "horarioSaida": "${txtHorarioSaida.text}"
                    }
                """.trimIndent()
            )

            val request = Request.Builder()
                .post(body)
                .url("https://fatec-mobile-default-rtdb.firebaseio.com/chamado.json")
                .build()
            val response = object : Callback {
                override fun onFailure(call: Call?, e: IOException?) {
                    Log.e("AGENDA-CHAMADO", e?.message.toString())
                }

                override fun onResponse(call: Call?, response: Response?) {
                    val localBody = response?.body()
                    localBody?.charStream()?.buffered()?.lines()?.forEach {
                        Log.i("AGENDA-CHAMADO", it.toString())
                    }

                }
            }
            clientHttp.newCall(request).enqueue(response)
        }
    }
}