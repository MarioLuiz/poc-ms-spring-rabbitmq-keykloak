package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.infra.clients

import feign.Param
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model.Cartao
import io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito.domain.model.CartaoCliente
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "mscartoes", path = "/cartoes")
interface CartoesResourceClient {

    @GetMapping(params = ["cpf"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCartoesByCliente(@RequestParam("cpf") cpf: String): ResponseEntity<List<CartaoCliente>>

    @GetMapping(params = ["renda"])
    fun getCartoesRendaAte(@RequestParam(value = "renda") renda: Long): ResponseEntity<List<Cartao>>
}