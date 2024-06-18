package com.example.agendacontatorecyclevioewfirebasebinding.recycle.chamado

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.agendacontatorecyclevioewfirebasebinding.R
import com.example.agendacontatorecyclevioewfirebasebinding.model.Chamado
import com.example.agendacontatorecyclevioewfirebasebinding.model.Contato
import com.example.agendacontatorecyclevioewfirebasebinding.repository.ChamadoRepository
import com.example.agendacontatorecyclevioewfirebasebinding.repository.DependenteRepository

class ChamadoAdapter(private val contexto: Context, private val lista: ArrayList<Chamado>) :
    RecyclerView.Adapter<ChamadoViewHolder>() {
    private var inflater: LayoutInflater = LayoutInflater.from(contexto)
    private var repositorio = ChamadoRepository()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChamadoViewHolder {
        val view = inflater.inflate(
            R.layout.chamado_row_layout,
            parent, false)
        return ChamadoViewHolder( view )
    }
    override fun getItemCount(): Int {
        return lista.size
    }
    override fun onBindViewHolder(holder: ChamadoViewHolder, position: Int) {
        val chamado = lista[position]
        holder.txtNome.text = "Nome do dependente: " + chamado.nome
        holder.txtRg.text = "RG do dependente: " + chamado.rg
        holder.txtTipoChamado.text = "Tipo do Chamado: " + chamado.tipoChamado

        holder.btnRowApagar.setOnClickListener {
            Log.i("AGENDA-CONTATO",
                "Contato selecionado para apagar: $chamado")
            repositorio.apagarChamado(chamado)
        }
    }
}