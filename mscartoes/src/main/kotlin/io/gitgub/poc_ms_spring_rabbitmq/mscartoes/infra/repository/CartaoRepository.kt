package io.gitgub.poc_ms_spring_rabbitmq.mscartoes.infra.repository

import io.gitgub.poc_ms_spring_rabbitmq.mscartoes.domain.Cartao
import org.springframework.data.jpa.repository.JpaRepository
import java.math.BigDecimal

interface CartaoRepository : JpaRepository<Cartao, Long> {
    fun findByRendaLessThanEqual(rendaBigDecimal: java.math.BigDecimal): kotlin.collections.List<io.gitgub.poc_ms_spring_rabbitmq.mscartoes.domain.Cartao>
}