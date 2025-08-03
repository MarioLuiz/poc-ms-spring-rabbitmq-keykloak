package io.gitgub.poc_ms_spring_rabbitmq.mscartoes.application

import io.gitgub.poc_ms_spring_rabbitmq.mscartoes.domain.Cartao
import io.gitgub.poc_ms_spring_rabbitmq.mscartoes.infra.repository.CartaoRepository
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CartaoService(
    private val repository: CartaoRepository
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun save(cartao: Cartao): Cartao {
        return repository.save(cartao)
    }

    fun getCartoesRendaMenorIgual(renda: Long): List<Cartao> {
        val rendaBigDecimal = BigDecimal.valueOf(renda)
        val cartoes = repository.findByRendaLessThanEqual(rendaBigDecimal)
        return cartoes
    }

}