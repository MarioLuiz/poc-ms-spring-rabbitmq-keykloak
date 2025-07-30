package io.gitgub.poc_ms_spring_rabbitmq.mscartoes.application

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("cartoes")
class CartoesResource {

    private val log: Logger = LoggerFactory.getLogger(CartoesResource::class.java)

    @GetMapping
    fun status(): String {
        log.info("Obtendo Status do mscartoes")
        return "Ok"
    }
}