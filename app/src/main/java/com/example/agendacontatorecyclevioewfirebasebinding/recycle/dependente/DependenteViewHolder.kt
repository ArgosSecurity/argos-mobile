package com.example.agendacontatorecyclevioewfirebasebinding.recycle.dependente

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agendacontatorecyclevioewfirebasebinding.R


class DependenteViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    var txtNome : TextView
    var txtRg : TextView
    var txtCnh : TextView
    var txtCpf : TextView
    var txtApto : TextView
    var txtResponsavel : TextView
    var btnRowApagar : Button
    init {
        txtNome = view.findViewById(R.id.txtRowNome)
        txtRg = view.findViewById(R.id.txtRowRg)
        txtCnh = view.findViewById(R.id.txtRowCnh)
        txtCpf = view.findViewById(R.id.txtRowCpf)
        txtApto = view.findViewById(R.id.txtRowApto)
        txtResponsavel = view.findViewById(R.id.txtRowResponsavel)
        btnRowApagar = view.findViewById(R.id.btnRowApagar)
    }
}