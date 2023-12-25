package com.stack.knowledge.thirdparty.feign.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@EnableFeignClients(basePackages = ["com.stack.knowledge.thirdparty.feign"])
@Configuration
class FeignConfig {

}