package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msclients.application.representation

import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msclients.domain.Cliente

class ClienteSaveRequest(
    val cpf: String,
    val nome: String,
    val idade: Int
) {
    fun toModel(): Cliente {
        return Cliente(cpf, nome, idade)
    }
}
