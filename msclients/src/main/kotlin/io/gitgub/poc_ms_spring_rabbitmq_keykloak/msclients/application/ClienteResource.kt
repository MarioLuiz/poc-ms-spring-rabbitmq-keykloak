package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msclients.application

import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msclients.application.representation.ClienteSaveRequest
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msclients.domain.Cliente
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("clientes")
class ClienteResource(
    private val service: ClienteService
) {
    private val log: Logger = LoggerFactory.getLogger(ClienteResource::class.java)

    @GetMapping
    fun status(): String {
        log.info("Obtendo o status do microservice de clientes")
        return "Ok"
    }

    @PostMapping
    fun save(@RequestBody request: ClienteSaveRequest): ResponseEntity<String> {
        val cliente = request.toModel()
        service.save(cliente)
        val headerLocation: URI = ServletUriComponentsBuilder.fromCurrentRequest()
            .query("cpf={cpf}").buildAndExpand(cliente.cpf).toUri()
        return ResponseEntity.created(headerLocation).build<String>()
    }

    @GetMapping(params = ["cpf"])
    fun dadosCliente(@RequestParam("cpf") cpf: String?): ResponseEntity<Cliente> {
        val cliente = service.getByCpf(cpf)
        if (cliente != null) {
            return ResponseEntity.ok(cliente)
        }
        return ResponseEntity.notFound().build<Cliente>()
    }

}