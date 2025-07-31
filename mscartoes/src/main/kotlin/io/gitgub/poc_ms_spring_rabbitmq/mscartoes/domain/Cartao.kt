package io.gitgub.poc_ms_spring_rabbitmq.mscartoes.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "cartao")
data class Cartao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val nome: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val bandeira: BandeiraCartao,

    @Column(nullable = false)
    val renda: BigDecimal,

    @Column(nullable = false)
    val limiteBasico: BigDecimal,
) {
    // Construtor padrão
    constructor() : this(0, "", BandeiraCartao.VISA, BigDecimal.ONE, BigDecimal.ZERO)

    constructor(nome: String, bandeira: BandeiraCartao, renda: BigDecimal, limiteBasico: BigDecimal)
            : this(0, nome, bandeira, renda, limiteBasico)
}