package com.tei.tenis.point.tenis.infrastracture.db

import org.springframework.data.repository.CrudRepository


interface GameRepositoryRedis : CrudRepository<GameEntity, String> {
    fun findByUserId(userId: String): Collection<GameEntity>
}