package com.tei.tenis.point.tenis.domain.game

class Gem(var p1Point: TennisPoint, var p2Point: TennisPoint) {

    val tieBreak: TieBreak = TieBreak();
    var winner: Player? = null
    var done: Boolean = false
    var isTieBreak: Boolean = false

    companion object Factory {
        fun startingPoint(): Gem = Gem(TennisPoint.ZERO, TennisPoint.ZERO)
        fun finishedGem(p1: TennisPoint, p2: TennisPoint): Gem {
            val gem = Gem(p1, p2)
            gem.done = true
            if (p1 > p2) {
                gem.winner = Player("p1")
            } else {
                gem.winner = Player("p2")
            }
            return gem
        }
        fun tieBreak(): Gem {
            val gem = Gem(TennisPoint.ZERO, TennisPoint.ZERO)
            gem.isTieBreak = true
            return gem;
        }
    }

    fun addPoint(player: Player) {
        updatePointsFor(player)
    }

    private fun updatePointsFor(player: Player) {
        val playerSide: String = player.side

        if (this.isTieBreak) {
            tieBreak.addPoint(player);
            if (tieBreak.done) {
                this.winner = tieBreak.winner
                this.done = true
            }
        } else {
            if (playerSide == "p1") {
                p1Point = addPoint(p1Point)
            } else {
                p2Point = addPoint(p2Point)
            }
        }

        if (done) {
            winner = player
        }
    }

    private fun addPoint(point: TennisPoint): TennisPoint {
        when (point) {
            TennisPoint.ZERO -> return TennisPoint.FIFTEEN
            TennisPoint.FIFTEEN -> return TennisPoint.THIRTY
            TennisPoint.THIRTY -> {
                return handleThirtyAddPoint()
            }

            TennisPoint.FORTY -> {
                done = true
                return TennisPoint.FORTY
            }

            TennisPoint.DEUCE -> {
                if (p1Point == TennisPoint.ADVANTAGE && p2Point == TennisPoint.DEUCE) {
                    p1Point = TennisPoint.DEUCE;
                    return TennisPoint.DEUCE
                } else if (p2Point == TennisPoint.ADVANTAGE && p1Point == TennisPoint.DEUCE) {
                    p2Point = TennisPoint.DEUCE
                    return TennisPoint.DEUCE;
                }
                return TennisPoint.ADVANTAGE
            }
            TennisPoint.ADVANTAGE -> {
                done = true
                return TennisPoint.ADVANTAGE
            }
        }
    }

    private fun handleThirtyAddPoint() = if (p2Point == TennisPoint.FORTY) {
        p2Point = TennisPoint.DEUCE
        TennisPoint.DEUCE
    } else if (p1Point == TennisPoint.FORTY) {
        p1Point = TennisPoint.DEUCE
        TennisPoint.DEUCE
    } else {
        TennisPoint.FORTY
    }
}

class TieBreak() {
    var p1Points: Int = 0
    var p2Points: Int = 0
    var done: Boolean = false
    var winner: Player? = null

    fun addPoint(player: Player) {
        if (player.side == "p1") {
            p1Points++;
            if (p1Points == 7 && p2Points < 6) {
                done = true
                winner = Player("p1")
            } else if (p1Points > 7 && p1Points - p2Points == 2) {
                done = true
                winner = Player("p1")
            }
        } else {
            p2Points++;
            if (p2Points == 7 && p1Points < 6) {
                done = true
                winner = Player("p2")
            }else if (p2Points > 7 && p2Points - p1Points == 2) {
                done = true
                winner = Player("p2")
            }
        }
    }
}
