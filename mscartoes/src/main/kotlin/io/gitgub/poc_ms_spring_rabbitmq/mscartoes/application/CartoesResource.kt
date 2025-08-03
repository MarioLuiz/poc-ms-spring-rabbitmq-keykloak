package io.gitgub.poc_ms_spring_rabbitmq.mscartoes.application

import io.gitgub.poc_ms_spring_rabbitmq.mscartoes.application.representation.CartaRequestBody
import io.gitgub.poc_ms_spring_rabbitmq.mscartoes.domain.Cartao
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.query.Param
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("cartoes")
class CartoesResource (
    private val service: CartaoService
) {

    private val log: Logger = LoggerFactory.getLogger(CartoesResource::class.java)

    @GetMapping
    fun status(): String {
        log.info("Obtendo Status do mscartoes")
        return "Ok"
    }

    @PostMapping
    fun cadastra( @RequestBody request: CartaRequestBody) : ResponseEntity<String> {
        val cartao = request.toModel()
        service.save(cartao)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping(params =["renda"])
    fun getCartoesRendaAte(@Param(value = "renda") renda: Long) : ResponseEntity<List<Cartao>> {
        val list : List<Cartao> = service.getCartoesRendaMenorIgual(renda)
        //if (list.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        return ResponseEntity.status(HttpStatus.OK).body(list)
    }
}