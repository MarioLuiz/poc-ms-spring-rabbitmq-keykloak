package io.github.poc_ms_spring_rabbitmq_keykloak.eurekaserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class EurekaserverApplication

fun main(args: Array<String>) {
	runApplication<EurekaserverApplication>(*args)
}
