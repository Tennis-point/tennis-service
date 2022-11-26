package com.tei.tenis.point.tenis

import com.tei.tenis.point.tenis.app.GameRepository
import com.tei.tenis.point.tenis.domain.game.Game
import com.tei.tenis.point.tenis.infrastracture.db.GameEntity
import org.springframework.stereotype.Component
import java.util.*

class GameRepositoryFake : GameRepository {

    private val map: HashMap<String, GameEntity> = HashMap()

    override fun saveNew(game: Game, userId: String): GameEntity {
        val id: String = UUID.randomUUID().toString()
        map[id] = GameEntity.new(userId, id, game)
        return map[id]!!
    }

    override fun saveUpdate(id: String, game: Game) : Game {
        val userId = map[id]?.userId ?: throw IllegalStateException("No game found for id passed.")
        map[id] = GameEntity.new(userId, id, game)
        return game
    }

    override fun findByUserId(userId: String): Collection<GameEntity> {
        return map.filterValues { gameEntity -> gameEntity.userId == userId }.values
    }

    override fun findById(id: String): GameEntity {
        return map[id] ?: throw IllegalStateException("No game found for that id.")
    }
}
