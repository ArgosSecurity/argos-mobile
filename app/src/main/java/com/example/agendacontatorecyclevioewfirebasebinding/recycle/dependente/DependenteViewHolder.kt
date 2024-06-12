package com.example.agendacontatorecyclevioewfirebasebinding.recycle.chamado

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agendacontatorecyclevioewfirebasebinding.R


class DependenteViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    var txtNome : TextView
    var txtRg : TextView
    var btnRowApagar : Button
    init {
        txtNome = view.findViewById(R.id.txtRowNome)
        txtRg = view.findViewById(R.id.txtRowRg)
        btnRowApagar = view.findViewById(R.id.btnRowApagar)
    }
}