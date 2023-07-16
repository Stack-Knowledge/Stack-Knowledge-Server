package com.stack.knowledege

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import java.util.TimeZone

@SpringBootApplication
@ConfigurationPropertiesScan
class KnowledgeApplication

fun main(args: Array<String>) {
	TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
	runApplication<KnowledgeApplication>(*args)
}
