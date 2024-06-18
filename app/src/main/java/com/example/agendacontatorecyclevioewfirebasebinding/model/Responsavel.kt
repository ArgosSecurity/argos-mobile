package com.example.agendacontatorecyclevioewfirebasebinding.model

data class Responsavel (
    var id: String? = null,
    val rg: String = "",
    val nome: String = "",
    val dataNascimento: String = "",
    val cpf : String = "",
    val apto: String = "",
    val quantidadeDependentes : Int = 0
)