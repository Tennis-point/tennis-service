
import com.tei.tenis.point.domain.game.*
import com.tei.tenis.point.domain.game.Set
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class GameTest {

    @Test
    fun `should add point to player one in set 1 gem 1`() {
        // given fresh game object
        val game: Game = Game.startingPoint()
        // when addPoint to p1
        game.addPoint(game.p1)
        // then there is one set with one gem
        val sets = game.sets
        assertTrue(sets.size == 1)
        assertTrue(sets[0].gems.size == 1)
        // gem has 15 point value for player one and 0 point value for p2
        val gem: Gem = sets[0].gems[0]
        assertTrue(gem.p1Point == TennisPoint.FIFTEEN)
        assertTrue(gem.p2Point == TennisPoint.ZERO)
    }

    @Test
    fun `should add point to player one in set 1 gem 1 for 30 - 0`() {
        // given fresh game object
        val game: Game = Game.startingPoint()
        // when addPoint to p1
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        // then there is one set with one gem
        val sets = game.sets
        assertTrue(sets.size == 1)
        assertTrue(sets[0].gems.size == 1)
        // gem has 30 point value for player one and 0 point value for p2
        val gem: Gem = sets[0].gems[0]
        assertTrue(gem.p1Point == TennisPoint.THIRTY)
        assertTrue(gem.p2Point == TennisPoint.ZERO)
    }

    @Test
    fun `should add point to player one in set 1 gem 1 for 30 - 30`() {
        // given fresh game object
        val game: Game = Game.startingPoint()
        // when addPoint to p1
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p2)
        game.addPoint(game.p2)
        // then there is one set with one gem
        val sets = game.sets
        assertTrue(sets.size == 1)
        assertTrue(sets[0].gems.size == 1)
        // gem has 30 point value for player one and 30 point value for p2
        val gem: Gem = sets[0].gems[0]
        assertEquals(gem.p1Point, TennisPoint.THIRTY)
        assertEquals(gem.p2Point, TennisPoint.THIRTY)
    }

    @Test
    fun `should add gem point to player one in set 1 gem 1 for 40 - 0, 1-0, 0-0`() {
        // given fresh game object
        val game: Game = Game.startingPoint()
        // when addPoint to p1 5 times
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        // then there is one set with one gem
        val sets = game.sets
        assertTrue(sets.size == 1)
        assertTrue(sets[0].gems.size == 2)
        // gem one has 40 point value for player one and 0 point value for p2
        val gem: Gem = sets[0].gems[0]
        assertEquals(TennisPoint.FORTY, gem.p1Point)
        assertEquals(TennisPoint.ZERO, gem.p2Point)
        assertEquals(true, gem.done)
    }

    @Test
    fun `should add set point to player two in set 1 gem 1 - 6`() {
        // given first set 1-5
        val game: Game = firstSet()
        // when addPoint to p2 1 for 1-6
        game.addPoint(game.p2)
        game.addPoint(game.p2)
        game.addPoint(game.p2)
        game.addPoint(game.p2)
        // then there are two sets
        val sets = game.sets
        assertEquals(sets[0].gems.size, 7)
        assertTrue(sets[0].done)
        assertTrue(sets[0].winner == Player("p2"))
    }

    @Test
    fun `should set game to done and winner p1`() {
        // given two sets 2-0 and gems 6-1 for p1
        val game: Game = secondSetLastGem()
        // when player one wins last gem
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        // then the game is done with two sets and the winner is p1
        val sets = game.sets
        assertEquals(sets[0].gems.size, 6)
        assertEquals(sets[1].gems.size, 7)
        assertEquals(2, sets.size)
        assertTrue(sets[0].done)
        assertTrue(sets[0].winner == Player("p1"))
        assertTrue(sets[1].done)
        assertTrue(sets[1].winner == Player("p1"))
        assertTrue(game.done)
        assertEquals(game.winner, Player("p1"))
    }

    @Test
    fun `should set both to deuce`() {
        // given
        val game: Game = Game.startingPoint()
        // when
        game.addPoint(game.p1)
        game.addPoint(game.p2)
        game.addPoint(game.p1)
        game.addPoint(game.p2)
        game.addPoint(game.p1)
        game.addPoint(game.p2)
        // then
        val sets = game.sets
        assertEquals(sets[0].gems.size, 1)
        assertEquals(1, sets.size)
        assertEquals(sets[0].gems[0].p2Point, TennisPoint.DEUCE)
        assertEquals(sets[0].gems[0].p1Point, TennisPoint.DEUCE)
    }

    @Test
    fun `should set p2 to win with advantage`() {
        // given
        val game: Game = Game.startingPoint()
        // when
        game.addPoint(game.p1)
        game.addPoint(game.p2)
        game.addPoint(game.p1)
        game.addPoint(game.p2)
        game.addPoint(game.p1)
        game.addPoint(game.p2)
        game.addPoint(game.p2)
        game.addPoint(game.p2)
        // then
        val sets = game.sets
        assertEquals(sets[0].gems.size, 1)
        assertEquals(1, sets.size)
        assertEquals(sets[0].gems[0].p2Point, TennisPoint.ADVANTAGE)
        assertEquals(sets[0].gems[0].p1Point, TennisPoint.DEUCE)
        assertEquals(sets[0].gems[0].done, true)
        assertEquals(sets[0].gems[0].winner, Player("p2"))
    }

    @Test
    fun should_start_tie_break_when_6_6_in_gems() {
        // given
        val game: Game = fiveToSixFirstSet();
        // when
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        // then
        val sets = game.sets
        assertEquals(sets[0].gems.size, 13)
        assertEquals(sets[0].gems[sets[0].gems.lastIndex].isTieBreak, true)
    }

    @Test
    fun should_count_points_in_tie_break_as_integers() {
        // given
        val game: Game = fiveToSixFirstSet();
        // when
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1) // tie-break

        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1) // 3:0

        game.addPoint(game.p2)
        game.addPoint(game.p2)
        game.addPoint(game.p2) // 3:3

        // then
        val sets = game.sets
        assertEquals(sets[0].gems.size, 13)
        val lastGem = sets[0].gems[sets[0].gems.lastIndex]
        assertEquals(lastGem.isTieBreak, true)
        assertEquals(lastGem.tieBreak.p1Points, 3)
        assertEquals(lastGem.tieBreak.p2Points, 3)
    }

    @Test
    fun should_set_winner_of_the_tie_break_to_p1_after_7_3() {
        // given
        val game: Game = fiveToSixFirstSet();
        // when
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1) // p1 wins, tie-break

        game.addPoint(game.p1)
        assertEquals(game.sets[0].gems[12].tieBreak.p1Points, 1)
        game.addPoint(game.p1)
        game.addPoint(game.p1) // 3:0

        game.addPoint(game.p2)
        game.addPoint(game.p2)
        game.addPoint(game.p2) // 3:3

        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1) // 7:3

        game.addPoint(game.p2) // 1:0 sets 0:0 gems 0:15 gem
        // then
        val sets = game.sets
        assertEquals(sets[0].gems.size, 13)
        assertEquals(sets[0].done, true)
        assertEquals(sets[0].winner, game.p1)
        val lastGemSetOne = sets[0].gems[sets[0].gems.lastIndex]
        assertEquals(lastGemSetOne.isTieBreak, true)
        assertEquals(lastGemSetOne.tieBreak.p1Points, 7)
        assertEquals(lastGemSetOne.tieBreak.p2Points, 3)
        val lastGemSetTwo = sets[1].gems[sets[1].gems.lastIndex]
        assertEquals(lastGemSetTwo.p1Point, TennisPoint.ZERO)
        assertEquals(lastGemSetTwo.p2Point, TennisPoint.FIFTEEN)
    }

    @Test
    fun should_not_end_set_if_none_of_the_players_have_two_points_of_advantage_9_9() {
        // given
        val game: Game = fiveToSixFirstSet();
        // when
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1) // tie-break

        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1) // 3:0

        game.addPoint(game.p2)
        game.addPoint(game.p2)
        game.addPoint(game.p2) // 3:3

        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1) // 6:3

        game.addPoint(game.p2)
        game.addPoint(game.p2)
        game.addPoint(game.p2) // 6:6

        game.addPoint(game.p1)
        game.addPoint(game.p2) // 7:7
        game.addPoint(game.p1)
        game.addPoint(game.p2) // 8:8

        game.addPoint(game.p1)
        game.addPoint(game.p2) // 9:9

        // then
        val sets = game.sets
        assertEquals(sets[0].gems.size, 13)
        assertEquals(sets[0].done, false)
        val lastGemSetOne = sets[0].gems[sets[0].gems.lastIndex]
        assertEquals(lastGemSetOne.isTieBreak, true)
        assertEquals(lastGemSetOne.tieBreak.p1Points, 9)
        assertEquals(lastGemSetOne.tieBreak.p2Points, 9)
    }

    @Test
    fun should_end_set_one_of_the_players_acquires_two_point_advantge_in_tie_break_11_9() {
        // given
        val game: Game = fiveToSixFirstSet();
        // when
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1)// tie-break

        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1) // 3:0

        game.addPoint(game.p2)
        game.addPoint(game.p2)
        game.addPoint(game.p2) // 3:3

        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1) // 6:3

        game.addPoint(game.p2)
        game.addPoint(game.p2)
        game.addPoint(game.p2) // 6:6

        game.addPoint(game.p1)
        game.addPoint(game.p2) // 7:7
        game.addPoint(game.p1)
        game.addPoint(game.p2) // 8:8

        game.addPoint(game.p1)
        game.addPoint(game.p2) // 9:9

        game.addPoint(game.p1)
        game.addPoint(game.p1) // 11 : 9

        game.addPoint(game.p2)
        // then
        val sets = game.sets
        assertEquals(sets[0].gems.size, 13)
        assertEquals(sets[0].done, true)
        val lastGemSetOne = sets[0].gems[sets[0].gems.lastIndex]
        assertEquals(lastGemSetOne.isTieBreak, true)
        assertEquals(lastGemSetOne.tieBreak.p1Points, 11)
        assertEquals(lastGemSetOne.tieBreak.p2Points, 9)
    }

    @Test
    fun should_go_back_to_deuce() {
        // given
        val game = Game.startingPoint()
        // when
        game.addPoint(game.p1)
        game.addPoint(game.p1)
        game.addPoint(game.p1) // 40:0

        game.addPoint(game.p2)
        game.addPoint(game.p2)
        game.addPoint(game.p2) // DEUCE : DEUCE

        game.addPoint(game.p1) // Advantage : DEUCE

        game.addPoint(game.p2) // DEUCE : DEUCE
        // then
        assertEquals(game.sets[0].gems.filter { gem -> !gem.done }[0].p1Point, TennisPoint.DEUCE)
        assertEquals(game.sets[0].gems.filter { gem -> !gem.done }[0].p2Point, TennisPoint.DEUCE)
    }

    fun firstSet(): Game = Game(
        Player("p1"), Player("p2"), mutableListOf(
            Set(
                mutableListOf(
                    Gem.finishedGem(TennisPoint.ZERO, TennisPoint.FORTY), // 0:1
                    Gem.finishedGem(TennisPoint.ZERO, TennisPoint.FORTY), // 0:2
                    Gem.finishedGem(TennisPoint.ZERO, TennisPoint.FORTY), // 0:3
                    Gem.finishedGem(TennisPoint.ZERO, TennisPoint.FORTY), // 0:4
                    Gem.finishedGem(TennisPoint.ZERO, TennisPoint.FORTY), // 0:5
                    Gem.finishedGem(TennisPoint.FORTY, TennisPoint.ZERO), // 1:5
                )
            )
        )
    )

    fun fiveToSixFirstSet(): Game =
        Game(
            Player("p1"), Player("p2"), mutableListOf(
                Set(
                    mutableListOf(
                        Gem.finishedGem(TennisPoint.ZERO, TennisPoint.FORTY), // 0:1
                        Gem.finishedGem(TennisPoint.ZERO, TennisPoint.FORTY), // 0:2
                        Gem.finishedGem(TennisPoint.ZERO, TennisPoint.FORTY), // 0:3
                        Gem.finishedGem(TennisPoint.ZERO, TennisPoint.FORTY), // 0:4
                        Gem.finishedGem(TennisPoint.ZERO, TennisPoint.FORTY), // 0:5

                        Gem.finishedGem(TennisPoint.FORTY, TennisPoint.ZERO), // 1:5
                        Gem.finishedGem(TennisPoint.FORTY, TennisPoint.ZERO), // 2:5
                        Gem.finishedGem(TennisPoint.FORTY, TennisPoint.ZERO), // 3:5
                        Gem.finishedGem(TennisPoint.FORTY, TennisPoint.ZERO), // 4:5
                        Gem.finishedGem(TennisPoint.FORTY, TennisPoint.ZERO), // 5:5

                        Gem.finishedGem(TennisPoint.ZERO, TennisPoint.FORTY), // 5:6
                    )
                )
            )
        )

    fun secondSetLastGem(): Game {
        val set = Set(
            mutableListOf(
                Gem.finishedGem(TennisPoint.FORTY, TennisPoint.ZERO), // 1:0
                Gem.finishedGem(TennisPoint.FORTY, TennisPoint.ZERO), // 2:0
                Gem.finishedGem(TennisPoint.FORTY, TennisPoint.ZERO), // 3:0
                Gem.finishedGem(TennisPoint.FORTY, TennisPoint.ZERO), // 4:0
                Gem.finishedGem(TennisPoint.FORTY, TennisPoint.ZERO), // 5:0
                Gem.finishedGem(TennisPoint.FORTY, TennisPoint.ZERO), // 6:0
            )
        )
        set.done = true
        set.winner = Player("p1")

        return Game(
            Player("p1"), Player("p2"), mutableListOf(
                set,
                Set(
                    mutableListOf(
                        Gem.finishedGem(TennisPoint.ZERO, TennisPoint.FORTY), // 0:1
                        Gem.finishedGem(TennisPoint.FORTY, TennisPoint.ZERO), // 1:1
                        Gem.finishedGem(TennisPoint.FORTY, TennisPoint.FIFTEEN), // 2:1
                        Gem.finishedGem(TennisPoint.FORTY, TennisPoint.THIRTY), // 3:1
                        Gem.finishedGem(TennisPoint.ADVANTAGE, TennisPoint.FORTY), // 4:1
                        Gem.finishedGem(TennisPoint.FORTY, TennisPoint.ZERO), // 5:1
                    )
                )
            )
        )
    }

}
