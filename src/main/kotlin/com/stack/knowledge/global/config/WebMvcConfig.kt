package com.stack.knowledge.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedMethods("GET", "POST", "PUT", "PATCH", "HEAD", "DELETE", "OPTIONS")
            .allowedOrigins(
                "http://localhost:3000",
                "https://port-0-stack-knowledge-server-1xxfe2bllyrfbtt.sel5.cloudtype.app",
                "https://stackknowledge.vercel.app",
                "https://stackknowledge-admin.vercel.app"
            )
            .allowCredentials(true)
    }
}