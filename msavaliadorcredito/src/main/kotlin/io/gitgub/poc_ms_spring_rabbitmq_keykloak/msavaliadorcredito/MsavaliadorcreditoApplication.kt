package io.gitgub.poc_ms_spring_rabbitmq_keykloak.msavaliadorcredito

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class MsavaliadorcreditoApplication

fun main(args: Array<String>) {
	runApplication<MsavaliadorcreditoApplication>(*args)
}
