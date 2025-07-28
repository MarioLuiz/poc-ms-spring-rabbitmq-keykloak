package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msclients.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "clientes")
data class Cliente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true, nullable = false)
    val cpf: String,

    @Column(nullable = false)
    var nome: String,

    @Column(nullable = false)
    var idade: Int
) {
    constructor() : this(0, "", "", 0)
    constructor(cpf: String, nome: String, idade: Int) : this(0, cpf, nome, idade)
}