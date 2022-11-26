package com.tei.tenis.point.tenis.app

import com.tei.tenis.point.tenis.domain.game.Game
import com.tei.tenis.point.tenis.domain.game.Player
import com.tei.tenis.point.tenis.infrastracture.db.GameEntity
import com.tei.tenis.point.tenis.infrastracture.db.GameRepositoryRedisImpl
import org.springframework.stereotype.Service

@Service
class TennisGameService(val gameRepository: GameRepositoryRedisImpl) {

    fun startGame(userId: String): GameEntity {
        // start the game domain object
        val game = Game.startingPoint();
        // save empty game in the db with the user id and timestamp
        return gameRepository.saveNew(game, userId);
    }

    fun get(userId: String): Collection<Game> {
        return gameRepository.findByUserId(userId).map { g -> g.game };
    }

    fun addPoint(id: String, side: String): Game {
        var gameEntity = gameRepository.findById(id);
        if (!gameEntity.game.done) {
            gameEntity.game.addPoint(Player(side));
            return gameRepository.saveUpdate(id, gameEntity.game);
        }

        throw IllegalStateException("This game is already over.");
    }
}