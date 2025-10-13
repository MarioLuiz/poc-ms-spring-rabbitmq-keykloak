package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application

import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model.DadosCliente
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model.SituacaoCliente
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.infra.clients.ClienteResourceClient
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AvaliadorDeCreditoService(
    private val clienteResourceClient: ClienteResourceClient,
) {

    fun obterSituacaoCliente(cpf: String) : SituacaoCliente {

        // TODO
        // obterDadosCliente - MsClientes
        // obter cartoes do cliente - MsCartoes
        try {
            val responseClient = clienteResourceClient.dadosCliente(cpf)
            println("Response status: ${responseClient.statusCode}")
            println("Response body: ${responseClient.body}")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val resposeEntity: ResponseEntity<DadosCliente> = clienteResourceClient.dadosCliente(cpf)
        val situacao = SituacaoCliente(resposeEntity.body, emptyList())
        return situacao
    }
}
