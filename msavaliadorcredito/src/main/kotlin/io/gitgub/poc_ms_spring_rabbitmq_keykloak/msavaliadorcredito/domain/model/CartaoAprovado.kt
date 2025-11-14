package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model

import java.math.BigDecimal

class CartaoAprovado (
    val cartao : String,
    val bandeira : String,
    val limiteAprovado: BigDecimal,
)
{

}