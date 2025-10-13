package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.infra.clients

import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model.DadosCliente
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "msclients", path = "/clientes")
interface ClienteResourceClient {

    @GetMapping(params = ["cpf"])
    fun dadosCliente(@RequestParam("cpf") cpf: String): ResponseEntity<DadosCliente>

}