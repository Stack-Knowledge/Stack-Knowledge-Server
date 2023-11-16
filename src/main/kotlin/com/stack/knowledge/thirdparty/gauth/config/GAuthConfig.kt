package com.stack.knowledge.thirdparty.gauth.config

import gauth.impl.GAuthImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GAuthConfig {
    @Bean
    fun gAuth() = GAuthImpl()
}