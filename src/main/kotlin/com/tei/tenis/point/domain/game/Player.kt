package com.tei.tenis.point.domain.game

class Player(val side: String){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (side != other.side) return false

        return true
    }

    override fun hashCode(): Int {
        return side.hashCode()
    }
}