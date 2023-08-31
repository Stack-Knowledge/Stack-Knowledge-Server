package com.stack.knowledge.global.security

import com.stack.knowledge.global.config.FilterConfig
import com.stack.knowledge.global.security.handler.CustomAccessDeniedHandler
import com.stack.knowledge.global.security.handler.CustomAuthenticationEntryPoint
import com.stack.knowledge.global.security.spi.JwtParserPort
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
    companion object {
        const val student = "STUDENT"
        const val teacher = "TEACHER"
    }

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

//            // auth
//            .antMatchers(HttpMethod.POST, "/auth").permitAll()
//            .antMatchers(HttpMethod.PATCH, "/auth").permitAll()
//
//            // item
//            .antMatchers(HttpMethod.GET , "/item").hasRole(student)
//
//            // image
//            .antMatchers(HttpMethod.POST, "/image").permitAll()
//            .antMatchers(HttpMethod.PATCH, "/image").permitAll()
//
//            // user
//            .antMatchers(HttpMethod.GET, "/user/scoring").hasRole(student)
//            .antMatchers(HttpMethod.POST, "/user/scoring/{solve_id}").hasRole(student)
//
//            // student
//            .antMatchers(HttpMethod.GET, "/student/ranking").authenticated()
//
//            // order
//            .antMatchers(HttpMethod.POST, "/order/{item_id}").hasRole(student)
//            .antMatchers(HttpMethod.GET, "/order").hasRole(teacher)
//            .antMatchers(HttpMethod.PATCH, "/order/{order_id}").hasRole(teacher)
//
//            // mission
//            .antMatchers(HttpMethod.GET, "/mission").hasRole(student)
//            .antMatchers(HttpMethod.GET, "/mission/{mission_id}").hasRole(student)
//            .antMatchers(HttpMethod.POST, "/mission").hasRole(teacher)
//
//            // solve
//            .antMatchers(HttpMethod.POST, "/solve/{mission_id}").hasRole(student)


            .anyRequest().permitAll()
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