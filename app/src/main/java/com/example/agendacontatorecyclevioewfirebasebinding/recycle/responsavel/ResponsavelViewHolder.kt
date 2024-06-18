package com.example.agendacontatorecyclevioewfirebasebinding.recycle.responsavel

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agendacontatorecyclevioewfirebasebinding.R


class ResponsavelViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    var txtNome : TextView
    var txtRg : TextView
    var txtDataNascimento : TextView
    var txtCpf : TextView
    var txtApto : TextView
    var txtQuantidadeDependentes : TextView
    var btnRowApagar : Button
    init {
        txtNome = view.findViewById(R.id.txtRowNome)
        txtRg = view.findViewById(R.id.txtRowRg)
        txtDataNascimento = view.findViewById(R.id.txtRowDataNascimento)
        txtCpf = view.findViewById(R.id.txtRowCpf)
        txtApto = view.findViewById(R.id.txtRowApto)
        txtQuantidadeDependentes = view.findViewById(R.id.txtRowQuantidadeDependentes)
        btnRowApagar = view.findViewById(R.id.btnRowApagar)
    }
}