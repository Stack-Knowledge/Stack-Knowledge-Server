package com.stack.knowledge.global.security

import com.stack.knowledge.domain.user.application.spi.UserPort
import com.stack.knowledge.global.config.FilterConfig
import com.stack.knowledge.global.security.handler.CustomAccessDeniedHandler
import com.stack.knowledge.global.security.handler.CustomAuthenticationEntryPoint
import com.stack.knowledge.global.security.handler.CustomLoginFailureHandler
import com.stack.knowledge.global.security.handler.CustomLoginSuccessHandler
import com.stack.knowledge.global.security.oauth.CustomOAuth2UserService
import com.stack.knowledge.global.security.principal.UserDetailsService
import com.stack.knowledge.global.security.spi.JwtGeneratorPort
import com.stack.knowledge.global.security.spi.JwtParserPort
import com.stack.knowledge.thirdparty.google.GoogleProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.web.cors.CorsUtils

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtParserPort: JwtParserPort,
    private val jwtGeneratorPort: JwtGeneratorPort,
    private val userPort: UserPort,
    private val googleProperties: GoogleProperties,
    private val customOAuth2UserService: CustomOAuth2UserService,
    private val userDetailsService: UserDetailsService
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
            .formLogin().disable()
            .httpBasic().disable()
            .oauth2Login { oauth2Login ->
                oauth2Login
//                    .authorizationEndpoint { authorizationEndpointConfig ->
//                        authorizationEndpointConfig.baseUri("/auth/oauth2/authorization/google")
//                    }
                    .loginProcessingUrl(googleProperties.loginProcessingUrl)
                    .successHandler(CustomLoginSuccessHandler(jwtGeneratorPort, userPort, googleProperties))
                    .failureHandler(CustomLoginFailureHandler())
                    .userInfoEndpoint().userService(customOAuth2UserService)
            }
            .httpBasic()
            .and()

            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .requestMatchers(RequestMatcher { request ->
                CorsUtils.isPreFlightRequest(request)
            }).permitAll()

            // health
            .antMatchers(HttpMethod.GET, "/health").permitAll()

            // auth
            .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
            .antMatchers(HttpMethod.POST, "/auth/teacher").permitAll()
            .antMatchers(HttpMethod.POST, "/auth/student").permitAll()
            .antMatchers(HttpMethod.PATCH, "/auth").permitAll()
            .antMatchers(HttpMethod.DELETE, "/auth").authenticated()

            // item
            .antMatchers(HttpMethod.GET , "/item").hasRole(student)

            // user
            .antMatchers(HttpMethod.GET, "/user/scoring").hasRole(teacher)
            .antMatchers(HttpMethod.GET, "/user/scoring/{solve_id}").hasRole(teacher)
            .antMatchers(HttpMethod.GET, "/user/teacher").hasRole(teacher)
            .antMatchers(HttpMethod.POST, "/user/scoring/{solve_id}").hasRole(teacher)
            .antMatchers(HttpMethod.PATCH, "/user/{user_id}").hasRole(teacher)

            // student
            .antMatchers(HttpMethod.GET, "/student/ranking").authenticated()
            .antMatchers(HttpMethod.GET, "/student/my").hasRole(student)
            .antMatchers(HttpMethod.POST, "/student/image").authenticated()

            // order
            .antMatchers(HttpMethod.POST, "/order").hasRole(student)
            .antMatchers(HttpMethod.GET, "/order").hasRole(teacher)
            .antMatchers(HttpMethod.PATCH, "/order").hasRole(teacher)

            // mission
            .antMatchers(HttpMethod.GET, "/mission").authenticated()
            .antMatchers(HttpMethod.GET, "/mission/{mission_id}").authenticated()
            .antMatchers(HttpMethod.POST, "/mission").hasRole(teacher)

            // solve
            .antMatchers(HttpMethod.POST, "/solve/{mission_id}").hasRole(student)

            .anyRequest().permitAll()
            .and()

            .exceptionHandling()
            .authenticationEntryPoint(CustomAuthenticationEntryPoint())
            .accessDeniedHandler(CustomAccessDeniedHandler())
            .and()

            .apply(FilterConfig(jwtParserPort))
            .and()
            .build()

    private fun oauth2Login(http: HttpSecurity) {
        http.oauth2Login { oauth2Login ->
            oauth2Login
                .authorizationEndpoint { authorizationEndpointConfig ->
                    authorizationEndpointConfig.baseUri(googleProperties.loginEndPointBaseUrl)
                }
                .loginProcessingUrl(googleProperties.loginProcessingUrl)
                .successHandler(CustomLoginSuccessHandler(jwtGeneratorPort, userPort, googleProperties))
                .failureHandler(CustomLoginFailureHandler())
        }
    }

    @Bean
    protected fun loginSuccessHandler() = CustomLoginSuccessHandler(jwtGeneratorPort, userPort, googleProperties)

    @Bean
    protected fun loginFailureHandler() = CustomLoginFailureHandler()

    @Bean
    fun authenticationManager(): AuthenticationManager? {
        val provider = DaoAuthenticationProvider()
        provider.setPasswordEncoder(passwordEncoder())
        provider.setUserDetailsService(userDetailsService)
        return ProviderManager(provider)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

}