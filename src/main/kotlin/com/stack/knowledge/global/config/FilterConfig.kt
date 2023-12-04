package com.stack.knowledge.global.config

import com.stack.knowledge.global.filter.ExceptionFilter
import com.stack.knowledge.global.filter.JwtRequestFilter
import com.stack.knowledge.global.filter.RequestLogFilter
import com.stack.knowledge.global.security.spi.JwtParserPort
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfig(
    private val jwtParserPort: JwtParserPort
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(RequestLogFilter(), UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(JwtRequestFilter(jwtParserPort), UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(ExceptionFilter(), JwtRequestFilter::class.java)
    }
}