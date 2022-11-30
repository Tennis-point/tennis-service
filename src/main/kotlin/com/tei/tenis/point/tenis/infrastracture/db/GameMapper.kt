package com.tei.tenis.point.tenis.infrastracture.db

import com.tei.tenis.point.tenis.domain.game.*
import com.tei.tenis.point.tenis.domain.game.Set
import java.util.*

class GameMapper {

    fun toDomain(gameEntity: GameEntity): Game {
        val sets = gameEntity.sets.map { setEntity -> toSet(setEntity) }.toMutableList()
        val game = Game(Player("p1"), Player("p2"), sets)
        game.done = !gameEntity.winner.isNullOrBlank()
        game.gameId = gameEntity.gameId
        return game
    }

    private fun toSet(setEntity: SetEntity): Set {
        val toList: MutableList<Gem> = setEntity.gems.map { gemEntity -> toGem(gemEntity) }.toMutableList()
        val s = Set(toList)
        s.done = !setEntity.winner.isNullOrBlank()
        if (s.done) {
            s.winner = Player(setEntity.winner!!)
        }
        s.setId = setEntity.setId
        return s
    }

    private fun toGem(gemEntity: GemEntity): Gem {
        val g = Gem(TennisPoint.valueOf(gemEntity.p1Points), TennisPoint.valueOf(gemEntity.p2Points))
        g.done = !gemEntity.winner.isNullOrBlank()
        if (g.done) {
            g.winner = Player(gemEntity.winner!!)
        }
        g.isTieBreak = gemEntity.p1Tie.toInt() > 0 || gemEntity.p2Tie.toInt() > 0
        if (g.isTieBreak) {
            val tieBreak = TieBreak()
            tieBreak.p1Points = gemEntity.p1Tie.toInt()
            tieBreak.p2Points = gemEntity.p2Tie.toInt()
        }
        g.gemId = gemEntity.gemId
        return g
    }

    fun toEntity(game: Game, userId: String): GameEntity {
        val ge = GameEntity()
        ge.winner = game.winner.toString()
        if (game.gameId == null) {
            game.gameId = UUID.randomUUID().toString()
        }
        ge.gameId = game.gameId!!
        ge.userId = userId
        ge.sets = game.sets.map { set -> toSetEntity(set) }.toMutableList()
        return ge
    }

    private fun toSetEntity(set: Set): SetEntity {
        val gemEntities = set.gems.map { g -> toGemEntity(g) }.toMutableList();

        if (set.setId == null) {
            set.setId = UUID.randomUUID().toString()
        }

        return SetEntity(set.setId!!, set.winner?.side, gemEntities)
    }

    private fun toGemEntity(g: Gem): GemEntity {
        if (g.gemId == null) {
            g.gemId = UUID.randomUUID().toString()
        }
        return GemEntity(
            g.gemId!!, g.p1Point.toString(), g.p2Point.toString(),
            g.tieBreak.p1Points.toString(), g.tieBreak.p2Points.toString(), g.winner?.side
        )
    }
}


