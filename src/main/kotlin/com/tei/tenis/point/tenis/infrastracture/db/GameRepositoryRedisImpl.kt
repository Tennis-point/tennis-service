package com.tei.tenis.point.tenis.infrastracture.db

import com.tei.tenis.point.tenis.app.GameRepository
import com.tei.tenis.point.tenis.domain.game.Game
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.*

@Component
class GameRepositoryRedisImpl(private val redis: RedisTemplate<String, GameEntity>, private val repositoryRedis: GameRepositoryRedis) : GameRepository {

    override fun saveNew(game: Game, userId: String): GameEntity {
        val id = UUID.randomUUID().toString()
        val new = GameEntity.new(userId, userId, game)
        redis.boundValueOps(id).set(new)
        return new
    }

    override fun findByUserId(userId: String): Collection<GameEntity> {
        return repositoryRedis.findByUserId(userId)
    }

    override fun findById(id: String): GameEntity {
        return repositoryRedis.findByIdOrNull(id) ?: throw IllegalStateException("User with id: $id does not exist");
    }

    override fun saveUpdate(id: String, game: Game): Game {
        var gameEntity: GameEntity = repositoryRedis.findByIdOrNull(id) ?: throw IllegalStateException("User with id: $id does not exist");
        val updated = GameEntity.new(gameEntity.id, id, game)
        redis.boundValueOps(id).set(updated);
        return updated.game
    }

}