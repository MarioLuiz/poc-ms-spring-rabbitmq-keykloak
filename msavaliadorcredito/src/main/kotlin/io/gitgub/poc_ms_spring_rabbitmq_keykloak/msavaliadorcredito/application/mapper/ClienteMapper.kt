package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application.mapper

import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model.ClienteResumidoDTO
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model.DadosCliente
import org.springframework.stereotype.Component

@Component
class ClienteMapper {
    fun toClienteResumidoDTO(dadosCliente: DadosCliente): ClienteResumidoDTO {
        return ClienteResumidoDTO(
            id = dadosCliente.id,
            nome = dadosCliente.nome
        )
    }
}