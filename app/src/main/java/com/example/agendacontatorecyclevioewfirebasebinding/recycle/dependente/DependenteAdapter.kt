package com.example.agendacontatorecyclevioewfirebasebinding.recycle.dependente

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.agendacontatorecyclevioewfirebasebinding.R
import com.example.agendacontatorecyclevioewfirebasebinding.model.Dependente
import com.example.agendacontatorecyclevioewfirebasebinding.repository.DependenteRepository

class DependenteAdapter(private val contexto: Context, private val lista: ArrayList<Dependente>) :
    RecyclerView.Adapter<DependenteViewHolder>() {
    private var inflater: LayoutInflater = LayoutInflater.from(contexto)
    private var repositorio = DependenteRepository()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DependenteViewHolder {
        val view = inflater.inflate(
            R.layout.dependente_row_layout,
            parent, false)
        return DependenteViewHolder( view )
    }
    override fun getItemCount(): Int {
        return lista.size
    }
    override fun onBindViewHolder(holder: DependenteViewHolder, position: Int) {
        val dependente = lista[position]
        holder.txtNome.text = "Nome: " + dependente.nome
        holder.txtRg.text = "RG: " + dependente.rg
        holder.txtCnh.text = "CNH: " + dependente.cnh
        holder.txtCpf.text = "CPF: " + dependente.cpf
        holder.txtApto.text = "Apto: " + dependente.apto
        holder.txtResponsavel.text = "Responsável: " + dependente.responsavel

        holder.btnRowApagar.setOnClickListener {
            Log.i("AGENDA-CONTATO",
                "Dependente selecionado para apagar: $dependente")
            repositorio.apagarDependente(dependente)
        }
    }
}