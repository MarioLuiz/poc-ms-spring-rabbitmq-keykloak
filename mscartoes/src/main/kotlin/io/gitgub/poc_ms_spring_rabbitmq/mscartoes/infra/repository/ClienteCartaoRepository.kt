package io.gitgub.poc_ms_spring_rabbitmq.mscartoes.infra.repository

import io.gitgub.poc_ms_spring_rabbitmq.mscartoes.domain.ClienteCartao
import org.springframework.data.jpa.repository.JpaRepository

interface ClienteCartaoRepository : JpaRepository<ClienteCartao, Long> {
    fun findByCpf(cpf: String): List<ClienteCartao>
}