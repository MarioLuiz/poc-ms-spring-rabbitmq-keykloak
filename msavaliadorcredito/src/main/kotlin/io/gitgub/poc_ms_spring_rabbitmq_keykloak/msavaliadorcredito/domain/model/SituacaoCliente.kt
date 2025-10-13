package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model


data class SituacaoCliente (
    val cliente: DadosCliente? = null,
    val cartoes: List<CartaoCliente>,
){}
