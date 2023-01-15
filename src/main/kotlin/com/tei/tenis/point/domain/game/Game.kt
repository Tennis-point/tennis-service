package com.tei.tenis.point.domain.game

class Game(val p1: Player, val p2: Player, val sets: MutableList<Set>) {
    var gameId: String? = null
    var winner: Player? = null
    var done: Boolean = false

    companion object Factory {
        fun startingPoint(): Game = Game(Player("p1"), Player("p2"), mutableListOf(Set(mutableListOf(Gem(TennisPoint.ZERO, TennisPoint.ZERO)))))
    }

    fun addPoint(player: Player) {
        var currentSet = getCurrentSet()
        val gemsActive = currentSet.gems.filter { gem -> !gem.done }

        if (gemsActive.isEmpty()) { // no current gem we need to add one or finish set
            val potentialWinner = getSetWinnerOrNull(currentSet)
            if (potentialWinner != null) { // we have a winner set is done
                currentSet.done = true
                currentSet.winner = potentialWinner
                handlePotentialGameOver()

                if (!done) {
                    currentSet = getCurrentSet();
                    val newGem = Gem.startingPoint()
                    currentSet.gems.add(newGem)
                    newGem.addPoint(player)
                }
            } else {
                if (shouldStartTieBreak(currentSet)) {
                    val tieBreak = Gem.tieBreak()
                    tieBreak.addPoint(player)
                    currentSet.gems.add(tieBreak);
                } else {
                    val newGem = Gem.startingPoint()
                    currentSet.gems.add(newGem)
                    newGem.addPoint(player)
                }
            }
        } else {
            gemsActive[0].addPoint(player)
            if (gemsActive[0].done) {
                val winner = getSetWinnerOrNull(getCurrentSet())
                if (winner != null) {
                    currentSet.done = true
                    currentSet.winner = winner
                    handlePotentialGameOver();
                }
            }
        }
    }

    private fun getCurrentSet() = sets.filter { set -> !set.done }[0]

    private fun shouldStartTieBreak(currentSet: Set) = getGemsWonForSetAndPlayer(currentSet, p1) == 6 && getGemsWonForSetAndPlayer(currentSet, p2) == 6

    private fun handlePotentialGameOver() {
        if (sets.size >= 2) {
            val p1SetsWon = getSetsWonFor("p1");
            val p2SetsWon = getSetsWonFor("p2")

            if (p1SetsWon == 0) {
                this.winner = p2
                done = true
            } else if (p2SetsWon == 0) {
                this.winner = p1
                done = true
            } else if (p1SetsWon == 3) {
                this.winner = p1
                done = true
            } else if (p2SetsWon == 3) {
                this.winner = p2
                done = true
            } else {
                sets.add(Set(mutableListOf()))
            }
        } else {
            sets.add(Set(mutableListOf()))
        }
    }

    private fun getSetsWonFor(playerSide: String) = sets.filter { set -> set.winner == Player(playerSide) }.size

    private fun getSetWinnerOrNull(currentSet: Set): Player? {
        val p1WonGems = getGemsWonForSetAndPlayer(currentSet, p1)
        val p2WonGems = getGemsWonForSetAndPlayer(currentSet, p2)

        return if (isThereAWinner(p1WonGems, p2WonGems)) {
            p1
        } else if (isThereAWinner(p2WonGems, p1WonGems)) {
            p2
        } else {
            null
        }
    }

    private fun getGemsWonForSetAndPlayer(currentSet: Set, player: Player) = currentSet.gems.filter { gem -> gem.winner == player }.size

    private fun isThereAWinner(potentialWinnerGems: Int, potentialLooserGems: Int) =
        potentialWinnerGems == 6 && potentialLooserGems < 5 || potentialWinnerGems == 7
}
