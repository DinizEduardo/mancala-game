import com.eduardodiniz.mancala.domain.domain.Board
import com.eduardodiniz.mancala.domain.domain.Game
import com.eduardodiniz.mancala.domain.domain.Pit
import com.eduardodiniz.mancala.domain.domain.enum.Player
import com.eduardodiniz.mancala.domain.domain.enum.Status
import com.eduardodiniz.mancala.domain.service.GameService
import com.eduardodiniz.mancala.restapi.controller.GameController
import com.eduardodiniz.mancala.restapi.mapper.SowPitMapper
import com.eduardodiniz.mancala.restapi.request.SowPitRequest
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.UUID

class GameControllerTest {

    private val gameService = mockk<GameService>()
    private val gameController = GameController(gameService)

    @Test
    fun `createGame should return CREATED status with game response`() {
        val game = Game(
            id = UUID.randomUUID(),
            playerTurn = Player.PLAYER_1,
            playersBoard = listOf(
                Board(
                    player = Player.PLAYER_1,
                    pits = listOf(
                        Pit(0, 6),
                        Pit(1, 6),
                        Pit(2, 6),
                        Pit(3, 6),
                        Pit(4, 6),
                        Pit(5, 6),
                    ),
                    store = Pit(6, 0)
                ),
                Board(
                    player = Player.PLAYER_2,
                    pits = listOf(
                        Pit(0, 6),
                        Pit(1, 6),
                        Pit(2, 6),
                        Pit(3, 6),
                        Pit(4, 6),
                        Pit(5, 6),
                    ),
                    store = Pit(6, 0)
                )
            ),
            status = Status.IN_PROGRESS,
            winner = null
        )
        every { gameService.createGame() } returns game

        val response: ResponseEntity<Any> = gameController.createGame()

        assertEquals(HttpStatus.CREATED, response.statusCode)
    }

    @Test
    fun `makeMove should return OK status with game response`() {
        val id = UUID.randomUUID()
        val request = SowPitRequest(
            gameId = id,
            pitIndex = 1,
            player = "PLAYER_1"
        )
        val sow = SowPitMapper.toDomain(request)
        val game = Game(
            id = id,
            playerTurn = Player.PLAYER_1,
            playersBoard = listOf(
                    Board(
                            player = Player.PLAYER_1,
                            pits = listOf(
                                    Pit(0, 6),
                                    Pit(1, 6),
                                    Pit(2, 6),
                                    Pit(3, 6),
                                    Pit(4, 6),
                                    Pit(5, 6),
                            ),
                            store = Pit(6, 0)
                    ),
                    Board(
                            player = Player.PLAYER_2,
                            pits = listOf(
                                    Pit(0, 6),
                                    Pit(1, 6),
                                    Pit(2, 6),
                                    Pit(3, 6),
                                    Pit(4, 6),
                                    Pit(5, 6),
                            ),
                            store = Pit(6, 0)
                    )
            ),
            status = Status.IN_PROGRESS,
            winner = null
        )
        every { gameService.makeMove(any()) } returns game

        val response: ResponseEntity<Any> = gameController.makeMove(request)

        assertEquals(HttpStatus.OK, response.statusCode)
    }
}
