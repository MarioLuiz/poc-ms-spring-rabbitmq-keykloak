package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model

data class RetornoAvaliacaoCliente(
   val cartoes: List<CartaoAprovado>
) {
}