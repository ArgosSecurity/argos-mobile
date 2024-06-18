package com.example.agendacontatorecyclevioewfirebasebinding.recycle.tranca

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.agendacontatorecyclevioewfirebasebinding.R
import com.example.agendacontatorecyclevioewfirebasebinding.model.Tranca
import com.example.agendacontatorecyclevioewfirebasebinding.repository.TrancaRepository

class TrancaAdapter(private val contexto: Context, private val lista: ArrayList<Tranca>) :
    RecyclerView.Adapter<TrancaViewHolder>() {
    private var inflater: LayoutInflater = LayoutInflater.from(contexto)
    private var repositorio = TrancaRepository()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrancaViewHolder {
        val view = inflater.inflate(
            R.layout.tranca_row_layout,
            parent, false)
        return TrancaViewHolder( view )
    }
    override fun getItemCount(): Int {
        return lista.size
    }
    override fun onBindViewHolder(holder: TrancaViewHolder, position: Int) {
        val tranca = lista[position]
        holder.txtLocalidade.text = "Localidade: " + tranca.localidade
        holder.txtIdRegistro.text = "ID do Registro: " + tranca.idRegistro
        holder.txtFabricante.text = "Fabricante: " + tranca.fabricante
        holder.txtModelo.text = "Modelo: " + tranca.modelo
        holder.txtTipoTranca.text = "Tipo da Tranca: " + tranca.tipoTranca

        holder.btnRowApagar.setOnClickListener {
            Log.i("AGENDA-CONTATO",
                "Tranca selecionado para apagar: $tranca")
            repositorio.apagarTranca(tranca)
        }
    }
}