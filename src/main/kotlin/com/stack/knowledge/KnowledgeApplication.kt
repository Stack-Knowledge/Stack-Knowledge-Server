package com.stack.knowledge

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*
import javax.annotation.PostConstruct

@SpringBootApplication
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
