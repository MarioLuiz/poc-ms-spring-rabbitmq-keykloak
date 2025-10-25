package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application

import feign.FeignException
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application.exception.DadosClienteNotFoundException
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application.exception.ErroComunicacaoMicroservicesException
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application.mapper.ClienteMapper
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model.SituacaoCliente
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.infra.clients.CartoesResourceClient
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.infra.clients.ClienteResourceClient
import org.springframework.http.HttpStatus
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
        } catch (e: FeignException.FeignClientException) {
            val status = e.status()
            if (status == HttpStatus.NOT_FOUND.value()) {
                throw DadosClienteNotFoundException()
            }
            throw ErroComunicacaoMicroservicesException(e.message ?: "", status)
        }
    }
}
