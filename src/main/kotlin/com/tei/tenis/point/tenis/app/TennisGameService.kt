package com.tei.tenis.point.tenis.app

import com.tei.tenis.point.tenis.domain.game.Game
import com.tei.tenis.point.tenis.domain.game.Player
import com.tei.tenis.point.tenis.infrastracture.db.GameEntity
import com.tei.tenis.point.tenis.infrastracture.db.GameMapper
import com.tei.tenis.point.tenis.infrastracture.db.GameRepositorySql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TennisGameService(val gameRepository: GameRepositorySql) {

    fun startGame(userId: String): Game {
        // start the game domain object
        val game = Game.startingPoint();
        // save empty game in the db with the user id and timestamp
        return GameMapper().toDomain(gameRepository.save(GameMapper().toEntity(game, userId)));
    }

    fun get(userId: String): Collection<GameEntity> {
        return gameRepository.findAll().toList();
    }

    fun addPoint(id: String, side: String): Game {
        val gameEntity: GameEntity = gameRepository.findById(id).orElseThrow { throw IllegalStateException("Game with this id does not exist.") };
        val game = GameMapper().toDomain(gameEntity)
        game.addPoint(Player(side));
        val gameEntityUpdated = GameMapper().toEntity(game, gameEntity.userId);
        val gameEntityUpdatedSaved = gameRepository.save(gameEntityUpdated);
        return GameMapper().toDomain(gameEntityUpdatedSaved);
    }

    fun gameById(gameId: String): Game {
        val gameEntity = gameRepository.findById(gameId).orElseThrow { throw IllegalStateException("there is no game for that id") }
        return GameMapper().toDomain(gameEntity)
    }
}