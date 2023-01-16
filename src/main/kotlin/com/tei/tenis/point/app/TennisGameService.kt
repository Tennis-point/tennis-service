package com.tei.tenis.point.app

import com.tei.tenis.point.domain.game.Game
import com.tei.tenis.point.domain.game.Player
import com.tei.tenis.point.infrastracture.db.GameEntity
import com.tei.tenis.point.infrastracture.db.GameMapper
import com.tei.tenis.point.infrastracture.db.GameRepositorySql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers
import java.net.http.HttpResponse.BodyHandlers
import java.util.*

@Service
class TennisGameService(val gameRepository: GameRepositorySql) {
    fun startGame(userId: String): GameEntity {
        // start the game domain object
        val game = Game.startingPoint();
        // save empty game in the db with the user id and timestamp
        return gameRepository.save(GameMapper().toEntity(game, userId));
    }

    fun get(userId: String): Collection<GameEntity> {
        return gameRepository.findByUserId(userId);
    }

    fun addPoint(id: String, side: String): GameEntity {
        val gameEntity: GameEntity = gameRepository.findById(id).orElseThrow { throw IllegalStateException("Game with this id does not exist.") }
        val game = GameMapper().toDomain(gameEntity)
        if (game.done) {
            return gameEntity;
        }
        game.addPoint(Player(side));
        val gameEntityUpdated = GameMapper().toEntity(game, gameEntity.userId);
        val gameEntityUpdatedSaved = gameRepository.save(gameEntityUpdated);
        return gameEntityUpdatedSaved;
    }
}