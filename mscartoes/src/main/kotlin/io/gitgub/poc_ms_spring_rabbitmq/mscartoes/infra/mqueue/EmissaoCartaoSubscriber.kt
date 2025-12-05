package io.gitgub.poc_ms_spring_rabbitmq.mscartoes.infra.mqueue

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class EmissaoCartaoSubscriber {

    @Value("\${mq.queues.emissao-cartoes}")
    private lateinit var fila : String

    @RabbitListener(queues = ["\${mq.queues.emissao-cartoes}"])
    fun receberSolicitacaoEmissao(@Payload payload: String) {
        println(payload)
    }
}