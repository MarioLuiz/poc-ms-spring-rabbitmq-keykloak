package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = arrayOf("/avaliacoes-credito"))
class AvaliadorCreditoController {

    @GetMapping
    fun status(): String{
        return "Ok"
    }
}