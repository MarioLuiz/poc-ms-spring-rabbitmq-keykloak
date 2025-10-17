package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application

import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application.mapper.ClienteMapper
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model.DadosCliente
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model.SituacaoCliente
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.infra.clients.ClienteResourceClient
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AvaliadorDeCreditoService(
    private val clienteResourceClient: ClienteResourceClient,
    private val clienteMapper: ClienteMapper
) {

    fun obterSituacaoCliente(cpf: String) : SituacaoCliente {

        // TODO
        // obter cartoes do cliente - MsCartoes
        try {
            val responseClient = clienteResourceClient.dadosCliente(cpf)
            println("Response status: ${responseClient.statusCode}")
            println("Response body: ${responseClient.body}")
            val dadosCliente = responseClient.body ?: throw RuntimeException("Cliente não encontrado")
            val clienteResumido = clienteMapper.toClienteResumidoDTO(dadosCliente)

            val resposeEntity: ResponseEntity<DadosCliente> = clienteResourceClient.dadosCliente(cpf)
            val situacao = SituacaoCliente(clienteResumido, emptyList())
            return situacao
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Erro ao consultar cliente", e)
        }
    }
}
