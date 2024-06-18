package com.example.agendacontatorecyclevioewfirebasebinding.recycle.chamado

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agendacontatorecyclevioewfirebasebinding.R


class ChamadoViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    var txtNumeroRegistroTag : TextView
    var txtTipoTag : TextView
    var txtHorarioEntrada : TextView
    var txtHorarioSaida : TextView
    var btnRowApagar : Button
    var txtTipoChamado : TextView
    init {
        txtNumeroRegistroTag = view.findViewById(R.id.txtRowNumeroRegistroTag)
        txtTipoTag = view.findViewById(R.id.txtRowTipoTag)
        txtHorarioEntrada = view.findViewById(R.id.txtRowHorarioEntrada)
        txtHorarioSaida = view.findViewById(R.id.txtRowHorarioSaida)
        btnRowApagar = view.findViewById(R.id.btnRowApagar)
        txtTipoChamado = view.findViewById(R.id.txtRowTipoChamado)
    }
}