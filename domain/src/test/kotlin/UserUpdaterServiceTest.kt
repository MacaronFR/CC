import adapters.users.UserNotFoundException
import entities.deck.Deck
import entities.users.UpdateUser
import entities.users.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import ports.UsersRepository
import services.users.UserUpdaterService
import java.util.*

class UserUpdaterServiceTest {

    companion object {
        @JvmStatic
        @BeforeAll
        fun init() {
            MockitoAnnotations.openMocks(this)
        }
    }

    val userRepo = Mockito.mock(UsersRepository::class.java)

    val id = UUID.randomUUID()
    val pseudo = "macaron"
    val deck: Deck = Mockito.mock(Deck::class.java)
    val userResult = User(id, pseudo, 4, deck)
    val userUpdaterService = UserUpdaterService(userRepo)

    @Test
    fun updateOk(){
        val pseudo = "newPseudo"
        val user = User(id, pseudo, 4, deck)
        `when`(userRepo.update(id, UpdateUser(pseudo))).thenReturn(user)
        Assertions.assertTrue(userUpdaterService.update(id, UpdateUser(pseudo)) == user)
    }

    @Test
    fun updateNotOk(){
        val badId = UUID.randomUUID()
        doAnswer { throw UserNotFoundException() }.`when`(userRepo).update(badId, UpdateUser())
        assertThrows<UserNotFoundException> {
            userUpdaterService.update(badId, UpdateUser())
        }
    }

}