package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msclients.infra.repository

import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msclients.domain.Cliente
import org.springframework.data.jpa.repository.JpaRepository

interface ClienteRepository : JpaRepository<Cliente, Long>{
    fun findByCpf(cpf: String?): Cliente?
}