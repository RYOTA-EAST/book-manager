package com.book.manager.manager.presentation.config

import com.book.manager.AuthenticationService
import com.book.manager.BookManagerUserDetailsService
import com.book.manager.domain.enum.RoleType
import com.book.manager.manager.presentation.handler.BookManagerAccessDeniedHandler
import com.book.manager.manager.presentation.handler.BookManagerAuthenticationEntryPoint
import com.book.manager.manager.presentation.handler.BookManagerAuthenticationFailureHandler
import com.book.manager.manager.presentation.handler.BookManagerAuthenticationSuccessHandler
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.WebSecurityConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.concurrent.AbstractExecutorService

@EnableWebSecurity
class SecurityConfig(private val authenticationService: AuthenticationService) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .mvcMatchers("/login").permitAll()
            .mvcMatchers("/admin/**").hasAuthority(RoleType.ADMIN.toString())
            .anyRequest().authenticated()
            .and()
            .csrf().disable()
            .formLogin()
            .loginProcessingUrl("/login")
            .usernameParameter("email") //emailをusername
            .passwordParameter("pass")
            .successHandler(BookManagerAuthenticationSuccessHandler()) //成功
            .failureHandler(BookManagerAuthenticationFailureHandler()) //失敗
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(BookManagerAuthenticationEntryPoint())
            .accessDeniedHandler(BookManagerAccessDeniedHandler())
            .and()
            .cors()
            .configurationSource(corsConfigurationSource())
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(BookManagerUserDetailsService(authenticationService))
            .passwordEncoder(BCryptPasswordEncoder())
    }
    // cors
    private fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL)
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL)
        corsConfiguration.addAllowedOrigin("http://localhost:8081")
        corsConfiguration.allowCredentials = true


        val corsConfigurationSource = UrlBasedCorsConfigurationSource()
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration)

        return corsConfigurationSource
    }
}