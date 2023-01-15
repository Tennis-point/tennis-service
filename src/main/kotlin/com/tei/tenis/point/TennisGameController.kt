package com.tei.tenis.point

import com.tei.tenis.point.app.TennisGameService
import com.tei.tenis.point.domain.game.Game
import com.tei.tenis.point.infrastracture.db.GameEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:4200"], maxAge = 3600)
class TennisGameController(
    private val gameService: TennisGameService
) {
    private val log: Logger = LoggerFactory.getLogger(TennisGameController::class.java)

    @PostMapping("/game/{userId}/start")
    fun start(@PathVariable("userId") userId: String): GameEntity {
        log.info("GET /game/{userId}/start : Starting game for user with id : $userId");
        return gameService.startGame(userId)
    }

    @GetMapping("/game/{userId}/")
    fun getByUser(@PathVariable("userId") userId: String): Collection<GameEntity> {
        log.info("GET /game/{userId}/ : Finding game for user: $userId")
        return gameService.get(userId);
    }

    @PostMapping("/game/{gameId}/point/{side}")
    fun addPoint(@PathVariable("gameId") gameId: String, @PathVariable("side") side: String): GameEntity {
        log.info("POST /game/{id}/point/{side} : Add point to $side, game $gameId")
        return gameService.addPoint(gameId, side);
    }

}
