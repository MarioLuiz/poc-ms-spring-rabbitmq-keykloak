package io.gitgub.poc_ms_spring_rabbitmq_keykloak.mscloudgateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableDiscoveryClient
class MscloudgatewayApplication {
	@Bean
	fun routes(builder: RouteLocatorBuilder): RouteLocator{
		return builder
			.routes()
			//.route(r -> r.path("/clientes/**").uri("lb://msclients"))
			.route { r -> r.path("/clientes/**").uri("lb://msclients") }
			.route { r -> r.path("/cartoes/**").uri("lb://mscartoes") }
			.route { r -> r.path("/avaliacoes-credito/**").uri("lb://msavaliadorcredito") }
			.build()
	}
}
fun main(args: Array<String>) {
	runApplication<MscloudgatewayApplication>(*args)
}
