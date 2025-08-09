package io.gitgub.poc_ms_spring_rabbitmq.mscartoes.application.representation

import io.gitgub.poc_ms_spring_rabbitmq.mscartoes.domain.ClienteCartao
import java.math.BigDecimal


class CartoesPorClienteResponse(
    nome: String,
    bandeira: String,
    limiteLiberado: BigDecimal,
) {
    companion object {
        fun fromModel(model: ClienteCartao): CartoesPorClienteResponse {
            return CartoesPorClienteResponse(
                model.cartao.nome,
                model.cartao.bandeira.toString(),
                model.limite
            )
        }
    }
}
