package io.gitgub.poc_ms_spring_rabbitmq.mscartoes.application.representation

import io.gitgub.poc_ms_spring_rabbitmq.mscartoes.domain.BandeiraCartao
import io.gitgub.poc_ms_spring_rabbitmq.mscartoes.domain.Cartao
import java.math.BigDecimal

class CartaRequestBody (
    val nome : String,
    val bandeira: BandeiraCartao,
    val renda: BigDecimal,
    val limite: BigDecimal
){
    fun toModel() : Cartao {
        return Cartao(nome, bandeira, renda, limite)
    }
}
