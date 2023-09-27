package com.stack.knowledge.global.security

import com.stack.knowledge.global.config.FilterConfig
import com.stack.knowledge.global.security.handler.CustomAccessDeniedHandler
import com.stack.knowledge.global.security.handler.CustomAuthenticationEntryPoint
import com.stack.knowledge.global.security.spi.JwtParserPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
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

//    @Bean
//    fun configure() = WebSecurityCustomizer {
//            it.ignoring()
//                .antMatchers(HttpMethod.POST, "/auth")
//                .antMatchers(HttpMethod.PATCH, "/auth")
//    }

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

//            // health
//            .antMatchers(HttpMethod.GET, "/health").permitAll()
//
//            // auth
//            .antMatchers(HttpMethod.POST, "/auth").permitAll()
//            .antMatchers(HttpMethod.PATCH, "/auth").permitAll()
//
//            // item
//            .antMatchers(HttpMethod.GET , "/item").hasRole(student)
//
//            // user
//            .antMatchers(HttpMethod.GET, "/user/scoring/{page}").hasRole(teacher)
//            .antMatchers(HttpMethod.GET, "/user/scoring/{solve_id}").hasRole(teacher)
//            .antMatchers(HttpMethod.POST, "/user/scoring/{solve_id}").hasRole(teacher)
//
//            // student
//            .antMatchers(HttpMethod.GET, "/student/ranking").authenticated()
//            .antMatchers(HttpMethod.GET, "/student/my").hasRole(student)
//            .antMatchers(HttpMethod.POST, "/student/image").hasRole(student)
//            .antMatchers(HttpMethod.PATCH, "/student/image").hasRole(student)
//
//            // order
//            .antMatchers(HttpMethod.POST, "/order/{item_id}").hasRole(student)
//            .antMatchers(HttpMethod.GET, "/order").hasRole(teacher)
//            .antMatchers(HttpMethod.PATCH, "/order/{order_id}").hasRole(teacher)
//
//            // mission
//            .antMatchers(HttpMethod.GET, "/mission").authenticated()
//            .antMatchers(HttpMethod.GET, "/mission/{mission_id}").authenticated()
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