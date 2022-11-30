package com.tei.tenis.point.tenis.app

import com.tei.tenis.point.tenis.GameRepositoryFake
import com.tei.tenis.point.tenis.domain.game.Game
import com.tei.tenis.point.tenis.domain.game.TennisPoint
import com.tei.tenis.point.tenis.infrastracture.db.GameEntity
import com.tei.tenis.point.tenis.infrastracture.db.GameMapper
import com.tei.tenis.point.tenis.infrastracture.db.GameRepositorySql
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.util.*

internal class TennisGameServiceTest {

    val repository: GameRepositorySql = GameRepositoryFake()
    val tennisService: TennisGameService = TennisGameService(repository);

    @Test
    fun addPoint() {
        // given
        val game = Game.startingPoint()
        game.gameId = "gameId"
        val save = repository.save(GameMapper().toEntity(game, "1"))
        // when
        val g = tennisService.addPoint("gameId", "p1");
        // then
        assertEquals(TennisPoint.FIFTEEN, g.sets[0].gems[0].p1Point)
        assertEquals(TennisPoint.ZERO, g.sets[0].gems[0].p2Point)
    }
}