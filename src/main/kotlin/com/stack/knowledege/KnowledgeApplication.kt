package com.stack.knowledege

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import java.util.TimeZone
import javax.annotation.PostConstruct

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableScheduling
class KnowledgeApplication {

	@PostConstruct
	fun applicationTimeZoneSetter() {
		val timeZone = TimeZone.getTimeZone("Asia/Seoul")
		TimeZone.setDefault(timeZone)
	}
}

fun main(args: Array<String>) {
	runApplication<KnowledgeApplication>(*args)
}
