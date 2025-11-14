package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application

import feign.FeignException
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application.exception.DadosClienteNotFoundException
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application.exception.ErroComunicacaoMicroservicesException
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application.mapper.ClienteMapper
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model.Cartao
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model.CartaoAprovado
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model.DadosCliente
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model.RetornoAvaliacaoCliente
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model.SituacaoCliente
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.infra.clients.CartoesResourceClient
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.infra.clients.ClienteResourceClient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class AvaliadorDeCreditoService(
    private val clienteResourceClient: ClienteResourceClient,
    private val cartoesResourceClient: CartoesResourceClient,
    private val clienteMapper: ClienteMapper
) {

    fun obterSituacaoCliente(cpf: String): SituacaoCliente {
        try {
            val responseClient = clienteResourceClient.dadosCliente(cpf)
            val dadosCliente = responseClient.body ?: throw RuntimeException("Cliente não encontrado")

            val responseCartoesClient = cartoesResourceClient.getCartoesByCliente(cpf)
            val dadosCartaoCliente =
                responseCartoesClient.body ?: throw RuntimeException("Cartao cliente não encontrado")

            val clienteResumido = clienteMapper.toClienteResumidoDTO(dadosCliente)
            val situacao = SituacaoCliente(clienteResumido, dadosCartaoCliente)
            return situacao
        } catch (e: FeignException.FeignClientException) {
            val status = e.status()
            if (status == HttpStatus.NOT_FOUND.value()) {
                throw DadosClienteNotFoundException()
            }
            throw ErroComunicacaoMicroservicesException(e.message ?: "", status)
        } catch (e: FeignException.FeignServerException) {
            val status = e.status()
            val message = e.message ?: ("Serviço indisponivel no momento, por favor tente mais tarde:" + e.message)
            throw ErroComunicacaoMicroservicesException(message, status)
        }
    }

    fun realizarAvaliacao(cpf: String, renda: BigDecimal): RetornoAvaliacaoCliente {
        try {
            val dadosClienteResponse: ResponseEntity<DadosCliente> = clienteResourceClient.dadosCliente(cpf)
            val cartoesResponse: ResponseEntity<List<Cartao>> = cartoesResourceClient.getCartoesRendaAte(renda.toLong())

            val dadosCliente: DadosCliente =
                dadosClienteResponse.body ?: throw RuntimeException("Dados do cliente nao encontrado")
            val cartoes: List<Cartao> =
                cartoesResponse.body ?: throw RuntimeException("Cartoes do cliente nao encontrado")

            val cartoesAprovados: List<CartaoAprovado> = cartoes.map { cartao ->
                val limiteBasico = cartao.limiteBasico
                val idade = BigDecimal.valueOf(dadosCliente.idade.toLong())

                val fator = idade.divide(BigDecimal.valueOf(10))
                val limiteAprovado = fator.multiply(limiteBasico)

                CartaoAprovado(cartao.nome, cartao.bandeira, limiteAprovado)
            }
            val avaliacaoCliente : RetornoAvaliacaoCliente = RetornoAvaliacaoCliente(cartoesAprovados)
            return avaliacaoCliente
        } catch (e: FeignException.FeignClientException) {
            val status = e.status()
            if (status == HttpStatus.NOT_FOUND.value()) {
                throw DadosClienteNotFoundException()
            }
            throw ErroComunicacaoMicroservicesException(e.message ?: "", status)
        } catch (e: FeignException.FeignServerException) {
            val status = e.status()
            val message = e.message ?: ("Serviço indisponivel no momento, por favor tente mais tarde:" + e.message)
            throw ErroComunicacaoMicroservicesException(message, status)
        }
    }
}
