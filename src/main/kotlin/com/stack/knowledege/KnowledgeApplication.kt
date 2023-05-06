package com.stack.knowledege

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class KnowledgeApplication

fun main(args: Array<String>) {
	runApplication<KnowledgeApplication>(*args)
}
