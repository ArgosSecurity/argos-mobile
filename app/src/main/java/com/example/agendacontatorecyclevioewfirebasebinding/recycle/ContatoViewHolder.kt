package com.example.agendacontatorecyclevioewfirebasebinding.recycle

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agendacontatorecyclevioewfirebasebinding.R


class ContatoViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    var txtNome : TextView
    var txtTelefone : TextView
    var txtEmail : TextView
    var btnRowApagar : Button
    init {
        txtNome = view.findViewById(R.id.txtRowNome)
        txtTelefone = view.findViewById(R.id.txtRowTelefone)
        txtEmail = view.findViewById(R.id.txtRowEmail)
        btnRowApagar = view.findViewById(R.id.btnRowApagar)
    }
}