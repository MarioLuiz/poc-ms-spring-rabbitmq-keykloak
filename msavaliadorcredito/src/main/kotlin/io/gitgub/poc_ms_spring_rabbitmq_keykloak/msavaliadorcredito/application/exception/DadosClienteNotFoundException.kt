package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application.exception

class DadosClienteNotFoundException : Exception {
    constructor() : super("Dados do cliente não encontrados para o CPF informado.")
}