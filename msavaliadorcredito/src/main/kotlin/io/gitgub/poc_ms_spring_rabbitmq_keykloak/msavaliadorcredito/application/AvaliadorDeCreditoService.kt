package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application

import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model.SituacaoCliente
import org.springframework.stereotype.Service

@Service
class AvaliadorDeCreditoService {

    fun obterSituacaoCliente(cpf: String) : SituacaoCliente {

        // TODO
        // obterDadosCliente - MsClientes
        // obter cartoes do cliente - MsCartoes
    }
}
