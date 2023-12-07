package com.stack.knowledge.global.health

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckWebAdapter {

    @GetMapping("/health")
    fun healthCheck() = "OK"
}