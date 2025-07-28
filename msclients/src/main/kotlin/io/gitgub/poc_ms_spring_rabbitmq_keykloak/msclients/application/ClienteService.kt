package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msclients.application

import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msclients.domain.Cliente
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msclients.infra.repository.ClienteRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ClienteService(
    private val repository: ClienteRepository
) {
    @Transactional
    fun save(cliente: Cliente): Cliente {
        return repository.save(cliente)
    }

    fun getByCpf(cpf: String?): Cliente? {
        return repository.findByCpf(cpf)
    }
}