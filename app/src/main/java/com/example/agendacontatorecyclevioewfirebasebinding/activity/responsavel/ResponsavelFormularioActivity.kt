package com.example.agendacontatorecyclevioewfirebasebinding.activity.responsavel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.agendacontatorecyclevioewfirebasebinding.R
import com.example.agendacontatorecyclevioewfirebasebinding.databinding.FormularioResponsavelLayoutBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class ResponsavelFormularioActivity : AppCompatActivity() {

    private var clientHttp = OkHttpClient()
    private lateinit var binding : FormularioResponsavelLayoutBinding

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = FormularioResponsavelLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // setContentView(R.layout.formulario_layout)
        // val btnGravar = findViewById<Button>(R.id.btnGravar)
        // val btnListagem = findViewById<Button>(R.id.btnListagem)
        binding.btnListagem.setOnClickListener {
            val intent = Intent(this, ResponsavelListagemActivity::class.java)
            startActivity(intent)
        }

        binding.btnGravar.setOnClickListener {
            val txtRg = findViewById<EditText>(R.id.txtResponsavelRg)
            val txtNome = findViewById<EditText>(R.id.txtResponsavelNome)
            val txtDataNascimento = findViewById<EditText>(R.id.txtResponsavelDataNascimento)
            val txtCpf = findViewById<EditText>(R.id.txtResponsavelCpf)
            val txtApto = findViewById<EditText>(R.id.txtResponsavelApto)
            val txtQuantidadeDependentes = findViewById<EditText>(R.id.txtResponsavelQuantidadeDependentes)

            val body = RequestBody.create(
                MediaType.parse("application/json"),
                """
                    {
                        "rg": "${txtRg.text}",
                        "nome": "${txtNome.text}",
                        "dataNascimento": "${txtDataNascimento.text}",
                        "cpf": "${txtCpf.text}",
                        "apto": "${txtApto.text}",
                        "quantidadeDependentes": ${txtQuantidadeDependentes.text}
                    }
                """.trimIndent()
            )

            val request = Request.Builder()
                .post(body)
                .url("https://fatec-mobile-default-rtdb.firebaseio.com/responsavel.json")
                .build()
            val response = object : Callback {
                override fun onFailure(call: Call?, e: IOException?) {
                    Log.e("AGENDA-RESPONSAVEL", e?.message.toString())
                }

                override fun onResponse(call: Call?, response: Response?) {
                    val localBody = response?.body()
                    localBody?.charStream()?.buffered()?.lines()?.forEach {
                        Log.i("AGENDA-RESPONSAVEL", it.toString())
                    }

                }
            }
            clientHttp.newCall(request).enqueue(response)
        }
    }
}