package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msclients.application

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("clientes")
class ClienteResource {

    @GetMapping
    fun status(): String{
        return "Ok"
    }
}