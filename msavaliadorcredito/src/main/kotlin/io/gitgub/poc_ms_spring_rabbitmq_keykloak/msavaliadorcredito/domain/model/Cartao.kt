package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model

import java.math.BigDecimal

class Cartao (
    val id: Long,
    val nome: String,
    val bandeira: String,
    val renda: BigDecimal,
    val limiteBasico: BigDecimal,
) {

}