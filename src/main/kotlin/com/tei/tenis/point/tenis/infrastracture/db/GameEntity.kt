package com.tei.tenis.point.tenis.infrastracture.db

import com.tei.tenis.point.tenis.domain.game.Game
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.io.Serializable

@RedisHash("game")
data class GameEntity(
    @Id val id: String,
    val userId: String,
    val game: Game
) {

    companion object {
        fun new(userId: String, id: String, game: Game): GameEntity {
            return GameEntity(id, userId, game);
        }
    }

}
