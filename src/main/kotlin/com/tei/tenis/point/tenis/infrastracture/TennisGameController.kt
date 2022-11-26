package com.tei.tenis.point.tenis.infrastracture

import com.fasterxml.jackson.databind.ObjectMapper
import com.tei.tenis.point.tenis.app.TennisGameService
import com.tei.tenis.point.tenis.domain.game.Game
import com.tei.tenis.point.tenis.infrastracture.db.GameEntity
import com.tei.tenis.point.tenis.infrastracture.db.GameRepositoryRedis
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@Slf4j
public class TennisGameController(
    private val gameService: TennisGameService,
    val repositoryRedis: GameRepositoryRedis
) {
    private val log: Logger = LoggerFactory.getLogger(TennisGameController::class.java)

    @PostMapping("/game/{userId}/start")
    fun start(@PathVariable("userId") userId: String): GameEntity {
        log.info("starting game for user with id : $userId");
        val startGame = gameService.startGame(userId)
        log.info(ObjectMapper().writeValueAsString(startGame));
        return startGame
    }

    @PostMapping("/game/{userId}/start/redis")
    fun startRedis(@PathVariable("userId") userId: String): GameEntity {
        log.info("starting game for user with id : $userId");
        val gameEntity = repositoryRedis.save(GameEntity.new(userId, UUID.randomUUID().toString(), Game.startingPoint()))
        return gameEntity
    }

    @GetMapping("/game/{userId}/")
    fun getByUser(@PathVariable("userId") userId: String): Collection<Game> {
        return gameService.get(userId);
    }

    @PostMapping("/game/{id}/point/{side}")
    fun addPoint(@PathVariable("id") id: String, @PathVariable("side") side: String): Game {
        return gameService.addPoint(id, side);
    }


}