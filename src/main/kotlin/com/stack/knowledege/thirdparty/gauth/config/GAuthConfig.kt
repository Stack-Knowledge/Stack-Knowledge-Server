package com.stack.knowledege.thirdparty.gauth.config

import gauth.GAuth
import gauth.impl.GAuthImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GAuthConfig {
    @Bean
    fun gauth(): GAuth = GAuthImpl()
}