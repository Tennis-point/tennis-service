package com.tei.tenis.point.tenis.infrastracture

import com.tei.tenis.point.tenis.app.GameRepository
import com.tei.tenis.point.tenis.infrastracture.db.GameEntity
import com.tei.tenis.point.tenis.infrastracture.db.GameRepositoryRedisImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer


@Configuration
class AppConfig {

    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory? {
        return LettuceConnectionFactory()
    }

    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory?): RedisTemplate<String, GameEntity>? {
        val template = RedisTemplate<String, GameEntity>()
        template.valueSerializer = GenericJackson2JsonRedisSerializer();
        template.setConnectionFactory(redisConnectionFactory!!)
        return template
    }

}