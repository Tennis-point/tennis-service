package com.tei.tenis.point.infrastracture.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
public interface GameRepositorySql : JpaRepository<GameEntity, String> {
    abstract fun findByUserId(userId: String): Collection<GameEntity>

}