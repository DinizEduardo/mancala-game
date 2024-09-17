import com.eduardodiniz.mancala.domain.domain.SowPit
import com.eduardodiniz.mancala.domain.domain.enum.Player
import com.eduardodiniz.mancala.restapi.mapper.SowPitMapper
import com.eduardodiniz.mancala.restapi.request.SowPitRequest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class SowPitMapperTest {

    @Test
    fun `should map SowPitRequest to SowPit correctly`() {
        val request = SowPitRequest(
            gameId = UUID.randomUUID(),
            pitIndex = 3,
            player = "PLAYER_1"
        )

        val domain: SowPit = SowPitMapper.toDomain(request)

        assertEquals(request.gameId, domain.gameId)
        assertEquals(request.pitIndex, domain.pitIndex)
        assertEquals(Player.PLAYER_1, domain.player)
    }

    @Test
    fun `should throw IllegalArgumentException if player value is invalid`() {
        val request = SowPitRequest(
            gameId = UUID.randomUUID(),
            pitIndex = 3,
            player = "INVALID_PLAYER"
        )

        val exception = assertThrows<IllegalArgumentException> {
            SowPitMapper.toDomain(request)
        }

        assertTrue(exception.message!!.contains("No enum constant"))
    }
}
