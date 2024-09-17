import com.eduardodiniz.mancala.domain.domain.Board
import com.eduardodiniz.mancala.domain.domain.Game
import com.eduardodiniz.mancala.domain.domain.Pit
import com.eduardodiniz.mancala.domain.domain.enum.Player
import com.eduardodiniz.mancala.domain.domain.enum.Status
import com.eduardodiniz.mancala.restapi.mapper.GameMapper
import com.eduardodiniz.mancala.restapi.response.GameResponse
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.UUID

class GameMapperTest {

    @Test
    fun `should map Game to GameResponse correctly`() {
        val game = Game(
            id = UUID.randomUUID(),
            playersBoard = listOf(
                Board(
                    pits = listOf(
                        Pit(0, 4),
                        Pit(1, 5),
                        Pit(2, 6)
                    ),
                    store = Pit(6, 2),
                    player = Player.PLAYER_1
                ),
                Board(
                    pits = listOf(
                        Pit(0, 4),
                        Pit(1, 5),
                        Pit(2, 6)
                    ),
                    store = Pit(6, 3),
                    player = Player.PLAYER_2
                )
            ),
            playerTurn = Player.PLAYER_1,
            winner = null,
            status = Status.IN_PROGRESS
        )

        val gameResponse: GameResponse = GameMapper.toResponse(game)

        assertEquals(game.id.toString(), gameResponse.id)
        assertEquals(game.playersBoard.size, gameResponse.playersBoard.size)

        assertEquals(game.playerTurn.name, gameResponse.playerTurn)
        assertEquals(game.status.name, gameResponse.status)

        assertNull(gameResponse.winner)

        val boardResponse1 = gameResponse.playersBoard[0]
        assertEquals("PLAYER_1", boardResponse1.player)
        assertEquals(3, boardResponse1.smallPits.size)
        assertEquals(2, boardResponse1.bigPit.stones)

        val boardResponse2 = gameResponse.playersBoard[1]
        assertEquals("PLAYER_2", boardResponse2.player)
        assertEquals(3, boardResponse2.smallPits.size)
        assertEquals(3, boardResponse2.bigPit.stones)

        val pitResponse1 = boardResponse1.smallPits[0]
        assertEquals(0, pitResponse1.index)
        assertEquals(4, pitResponse1.stones)

        val pitResponse2 = boardResponse2.smallPits[1]
        assertEquals(1, pitResponse2.index)
        assertEquals(5, pitResponse2.stones)
    }
}
