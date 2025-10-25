package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application.exception

class ErroComunicacaoMicroservicesException(
    message: String,
    val statusCode: Int
) : Exception(message)