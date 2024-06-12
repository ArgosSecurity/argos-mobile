package com.example.agendacontatorecyclevioewfirebasebinding.recycle.responsavel

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.agendacontatorecyclevioewfirebasebinding.R
import com.example.agendacontatorecyclevioewfirebasebinding.model.Responsavel
import com.example.agendacontatorecyclevioewfirebasebinding.repository.ResponsavelRepository

class ResponsavelAdapter(private val contexto: Context, private val lista: ArrayList<Responsavel>) :
    RecyclerView.Adapter<ResponsavelViewHolder>() {
    private var inflater: LayoutInflater = LayoutInflater.from(contexto)
    private var repositorio = ResponsavelRepository()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResponsavelViewHolder {
        val view = inflater.inflate(
            R.layout.responsavel_row_layout,
            parent, false
        )
        return ResponsavelViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ResponsavelViewHolder, position: Int) {
        val responsavel = lista[position]
        holder.txtNome.text = "Nome do Responsável: " + responsavel.nome
        holder.txtRg.text = "RG do Responsável: " + responsavel.rg
        holder.txtApto.text = "Apto: " + responsavel.apto

        holder.btnRowApagar.setOnClickListener {
            Log.i("AGENDA-RESPONSAVEL",
                "Responsável selecionado para apagar: $responsavel")
            repositorio.apagarResponsavel(responsavel)
        }
    }
}