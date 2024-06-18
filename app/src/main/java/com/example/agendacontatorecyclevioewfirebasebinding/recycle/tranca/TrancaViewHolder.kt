package com.example.agendacontatorecyclevioewfirebasebinding.recycle.tranca

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agendacontatorecyclevioewfirebasebinding.R


class TrancaViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    var txtLocalidade : TextView
    var txtIdRegistro : TextView
    var txtFabricante : TextView
    var txtModelo : TextView
    var txtTipoTranca : TextView
    var btnRowApagar : Button
    init {
        txtLocalidade = view.findViewById(R.id.txtRowLocalidade)
        txtIdRegistro = view.findViewById(R.id.txtRowIdRegistro)
        txtFabricante = view.findViewById(R.id.txtRowFabricante)
        txtModelo = view.findViewById(R.id.txtRowModelo)
        txtTipoTranca = view.findViewById(R.id.txtRowTipoTranca)
        btnRowApagar = view.findViewById(R.id.btnRowApagar)
    }
}