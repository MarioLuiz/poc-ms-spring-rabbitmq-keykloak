package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application

import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application.mapper.ClienteMapper
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model.SituacaoCliente
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.infra.clients.CartoesResourceClient
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.infra.clients.ClienteResourceClient
import org.springframework.stereotype.Service

@Service
class AvaliadorDeCreditoService(
    private val clienteResourceClient: ClienteResourceClient,
    private val cartoesResourceClient: CartoesResourceClient,
    private val clienteMapper: ClienteMapper
) {

    fun obterSituacaoCliente(cpf: String) : SituacaoCliente {
        try {
            val responseClient = clienteResourceClient.dadosCliente(cpf)
            val dadosCliente = responseClient.body ?: throw RuntimeException("Cliente não encontrado")

            val responseCartoesClient = cartoesResourceClient.getCartoesByCliente(cpf)
            val dadosCartaoCliente = responseCartoesClient.body ?: throw RuntimeException("Cartao cliente não encontrado")

            val clienteResumido = clienteMapper.toClienteResumidoDTO(dadosCliente)
            val situacao = SituacaoCliente(clienteResumido, dadosCartaoCliente)
            return situacao
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Erro ao consultar cliente", e)
        }
    }
}
