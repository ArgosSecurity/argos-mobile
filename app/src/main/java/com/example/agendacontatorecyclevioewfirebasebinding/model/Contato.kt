package com.example.agendacontatorecyclevioewfirebasebinding.model

data class Contato (
    var id : String? = null,
    var nome : String = "",
    var telefone : String = "",
    var email : String = ""
    ) {
    override fun toString() : String {
        return this.nome + "-" + this.telefone
    }
}
