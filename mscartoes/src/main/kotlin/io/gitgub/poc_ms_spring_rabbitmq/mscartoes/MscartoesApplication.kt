package io.gitgub.poc_ms_spring_rabbitmq.mscartoes

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableRabbit
class MscartoesApplication
fun main(args: Array<String>) {
	runApplication<MscartoesApplication>(*args)
}
