package com.example.agendacontatorecyclevioewfirebasebinding.model

data class Chamado (
    var id : String? = null,
    var tipoChamado : String = "",
    var numeroRegistroTag : String = "",
    var tipoTag: String = "",
    var horarioEntrada: String = "",
    var horarioSaida: String = "",
)
