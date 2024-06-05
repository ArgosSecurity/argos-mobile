package com.example.myapp.recycle

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.argosmobile.R
import com.example.argosmobile.model.Tranca
import kotlinx.android.synthetic.main.tranca_row_layout.view.*

class TrancaAdapter(private val context: Context, private val trancas: ArrayList<Tranca>) :
    RecyclerView.Adapter<TrancaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrancaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.tranca_row_layout, parent, false)
        return TrancaViewHolder(view)
    }

    override fun getItemCount(): Int = trancas.size

    override fun onBindViewHolder(holder: TrancaViewHolder, position: Int) {
        val tranca = trancas[position]
        holder.bind(tranca)
    }
}

class TrancaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(tranca: Tranca) {
        itemView.txtLocalidade.text = tranca.localidade
    }
}

