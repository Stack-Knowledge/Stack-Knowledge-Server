package com.stack.knowledege.global.security

import com.stack.knowledege.global.config.FilterConfig
import com.stack.knowledege.global.security.handler.CustomAccessDeniedHandler
import com.stack.knowledege.global.security.handler.CustomAuthenticationEntryPoint
import com.stack.knowledege.global.security.spi.JwtParserPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.web.cors.CorsUtils

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtParserPort: JwtParserPort
) {
    @Bean
    protected fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .cors()
            .and()
            .csrf().disable()
            .httpBasic().disable()

            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .requestMatchers(RequestMatcher { request ->
                CorsUtils.isPreFlightRequest(request)
            }).permitAll()

            .antMatchers("/auth/**").permitAll()
            .antMatchers("/item/**").authenticated()
            .antMatchers("/image/**").permitAll()
            .antMatchers("/user/**").permitAll()

            .anyRequest().authenticated()
            .and()

            .exceptionHandling()
            .authenticationEntryPoint(CustomAuthenticationEntryPoint())
            .accessDeniedHandler(CustomAccessDeniedHandler())
            .and()

            .apply(FilterConfig(jwtParserPort))
            .and()
            .build()

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}