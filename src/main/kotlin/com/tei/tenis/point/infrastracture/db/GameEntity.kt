package com.tei.tenis.point.infrastracture.db

import com.tei.tenis.point.domain.game.Game
import javax.persistence.*


@Table(name = "GAME")
@Entity
class GameEntity(
    @Id var gameId: String = "",
    var userId: String = "",
    var winner: String? = null,
    @OneToMany(orphanRemoval = true, cascade = [CascadeType.ALL])
    @JoinColumn(name = "gameId")
    var sets: MutableList<SetEntity> = mutableListOf()
) {

    companion object {
        fun new(userId: String, id: String, game: Game): GameEntity {
            return GameEntity(id, userId, game.winner?.side);
        }
    }

}
