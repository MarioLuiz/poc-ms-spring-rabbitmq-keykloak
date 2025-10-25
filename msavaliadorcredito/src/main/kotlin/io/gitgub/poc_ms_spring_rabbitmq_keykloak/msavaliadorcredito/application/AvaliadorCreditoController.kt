package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application

import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application.exception.DadosClienteNotFoundException
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.application.exception.ErroComunicacaoMicroservicesException
import org.springframework.http.HttpStatus
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
    fun consultaSituacaoCliente(@RequestParam("cpf") cpf : String): ResponseEntity<Any> {
        try {
            val situacaoCliente = avaliadorDeCreditoService.obterSituacaoCliente(cpf)
            return ResponseEntity.ok(situacaoCliente)
        }catch (e : DadosClienteNotFoundException){
            return ResponseEntity.notFound().build()
        }catch (e : ErroComunicacaoMicroservicesException){
            val resolvedStatus = HttpStatus.resolve(e.statusCode)
            val httpStatus = resolvedStatus ?: HttpStatus.BAD_GATEWAY
            return ResponseEntity.status(httpStatus).body(e.message.toString())
        }
    }
}