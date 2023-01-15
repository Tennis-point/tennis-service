package com.tei.tenis.point.infrastracture.db.security

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterClass {

    @Bean
    fun jwtFilter(): FilterRegistrationBean<JwtFilter>? {
        val filter = FilterRegistrationBean<JwtFilter>()
        filter.filter = JwtFilter()
        filter.order = 1
        // provide endpoints which needs to be restricted.
        // All Endpoints would be restricted if unspecified
        //filter.addUrlPatterns("/game/1/start");
        return filter
    }

}