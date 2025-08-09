package io.gitgub.poc_ms_spring_rabbitmq.mscartoes.application

import io.gitgub.poc_ms_spring_rabbitmq.mscartoes.domain.ClienteCartao
import io.gitgub.poc_ms_spring_rabbitmq.mscartoes.infra.repository.ClienteCartaoRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service("ClienteCartaoService")
class ClienteCartaoService(
    private val repository: ClienteCartaoRepository
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun listCartoesByCpf(cpf: String): List<ClienteCartao> {
        return repository.findByCpf(cpf)
    }
}