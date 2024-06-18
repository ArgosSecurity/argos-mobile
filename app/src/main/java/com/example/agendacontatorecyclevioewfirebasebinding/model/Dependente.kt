package com.example.agendacontatorecyclevioewfirebasebinding.model

data class Dependente(
    var id: String? = null,
    val rg: String = "",
    val cnh: String = "",
    val cpf: String = "",
    val nome: String = "",
    val apto: String = "",
    var responsavel: String = "",
)