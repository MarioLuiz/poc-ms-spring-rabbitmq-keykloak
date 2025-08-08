package io.gitgub.poc_ms_spring_rabbitmq.mscartoes.application.representation

import io.gitgub.poc_ms_spring_rabbitmq.mscartoes.domain.Cartao
import io.gitgub.poc_ms_spring_rabbitmq.mscartoes.domain.ClienteCartao
import io.gitgub.poc_ms_spring_rabbitmq.mscartoes.infra.repository.CartaoRepository
import io.gitgub.poc_ms_spring_rabbitmq.mscartoes.infra.repository.ClienteCartaoRepository
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class ClienteCartaoService(
    private val repository: ClienteCartaoRepository
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun save(clienteCartao: ClienteCartao): ClienteCartao {
        return repository.save(clienteCartao)
    }

    fun listaCartoesByCpf(cpf: String): List<ClienteCartao> {
        return repository.findByCpf(cpf)
    }

}