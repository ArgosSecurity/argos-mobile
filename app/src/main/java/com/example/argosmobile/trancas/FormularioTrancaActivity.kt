package com.example.myapp.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.argosmobile.R
import com.example.argosmobile.model.Tranca
import com.example.argosmobile.util.ApiUtil
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException

class FormularioTrancaActivity : AppCompatActivity() {

    private lateinit var edtLocalidade: EditText
    private lateinit var btnSalvar: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_tranca)

        edtLocalidade = findViewById(R.id.edtLocalidade)
        btnSalvar = findViewById(R.id.btnSalvar)
        btnCancelar = findViewById(R.id.btnCancelar)

        btnSalvar.setOnClickListener {
            salvarTranca()
        }

        btnCancelar.setOnClickListener {
            finish()
        }
    }

    private fun salvarTranca() {
        val localidade = edtLocalidade.text.toString()

        if (localidade.isEmpty()) {
            Toast.makeText(this, "Preencha a localidade", Toast.LENGTH_SHORT).show()
            return
        }

        val tranca = Tranca(localidade = localidade)

        val json = Gson().toJson(tranca)
        val body = RequestBody.create("application/json".toMediaTypeOrNull(), json)
        val request = Request.Builder()
            .url(ApiUtil.BASE_URL + "/trancas")
            .post(body)
            .build()

        ApiUtil.clientHttp.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(this@FormularioTrancaActivity, "Erro ao salvar", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Toast.makeText(this@FormularioTrancaActivity, "Tranca salva com sucesso", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@FormularioTrancaActivity, "Erro ao salvar", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}