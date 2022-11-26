package com.tei.tenis.point.tenis.app

import com.tei.tenis.point.tenis.domain.game.Game
import com.tei.tenis.point.tenis.infrastracture.db.GameEntity

interface GameRepository {
    fun saveNew(game: Game, userId: String): GameEntity;
    fun findByUserId(userId: String) : Collection<GameEntity>;
    fun findById(id: String): GameEntity;
    fun saveUpdate(id: String, game: Game): Game
}