package com.tei.tenis.point.tenis

import com.tei.tenis.point.tenis.app.TennisGameService
import com.tei.tenis.point.tenis.domain.game.Game
import com.tei.tenis.point.tenis.infrastracture.db.GameEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TennisGameController(
    private val gameService: TennisGameService
) {
    private val log: Logger = LoggerFactory.getLogger(TennisGameController::class.java)

    @PostMapping("/game/{userId}/start")
    fun start(@PathVariable("userId") userId: String): Game {
        log.info("GET /game/{userId}/start : Starting game for user with id : $userId");
        return gameService.startGame(userId)
    }

    @GetMapping("/game/{userId}/")
    fun getByUser(@PathVariable("userId") userId: String): Collection<GameEntity> {
        log.info("GET /game/{userId}/ : Finding game for user: $userId")
        return gameService.get(userId);
    }

    @PostMapping("/game/{gameId}/point/{side}")
    fun addPoint(@PathVariable("gameId") gameId: String, @PathVariable("side") side: String): Game {
        log.info("POST /game/{id}/point/{side} : Add point to $side, game $gameId")
        return gameService.addPoint(gameId, side);
    }

    @GetMapping("/game/{gameId}/")
    fun getByGameId(@PathVariable("gameId") gameId: String): Game {
        log.info("GET /game/{gameId}/ : Finding game by gameId $gameId")
        return gameService.gameById(gameId);
    }
}
