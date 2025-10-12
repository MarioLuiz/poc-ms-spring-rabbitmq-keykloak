package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application

import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model.SituacaoCliente
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = arrayOf("/avaliacoes-credito"))
class AvaliadorCreditoController (
    private val avaliadorDeCreditoService: AvaliadorDeCreditoService
) {

    @GetMapping
    fun status(): String{
        return "Ok"
    }

    @GetMapping("situacao-cliente", params = ["cpf"])
    fun consultaSituacaoCliente(@RequestParam("cpf") cpf : String): ResponseEntity<SituacaoCliente> {
        val situacaoCliente = avaliadorDeCreditoService.obterSituacaoCliente(cpf)
    }
}