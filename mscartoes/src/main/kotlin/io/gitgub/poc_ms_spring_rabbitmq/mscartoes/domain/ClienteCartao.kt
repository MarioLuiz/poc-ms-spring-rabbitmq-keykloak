package io.gitgub.poc_ms_spring_rabbitmq.mscartoes.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal
@Entity
@Table(name = "cliente_cartao")
data class ClienteCartao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val cpf: String,

    @ManyToOne
    @JoinColumn(name = "id_cartao")
    val cartao: Cartao,

    @Column(nullable = false)
    val limite: BigDecimal,
){}

