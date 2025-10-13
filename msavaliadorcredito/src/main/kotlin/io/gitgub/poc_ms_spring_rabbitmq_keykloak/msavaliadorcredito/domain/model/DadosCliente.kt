package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model

data class DadosCliente(
    val id: Long,
    val cpf: String,
    val nome: String,
    val idade: Int
) {}
