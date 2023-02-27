import entities.deck.Deck
import entities.users.AddUser
import entities.users.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import ports.UsersRepository
import services.users.UserCreatorService
import java.util.*

class UserCreatorServiceTest {
    companion object {
        @JvmStatic
        @BeforeAll
        fun init() {
            MockitoAnnotations.openMocks(this)
        }
    }

    val userRepo: UsersRepository = mock(UsersRepository::class.java)

    val deck: Deck = mock(Deck::class.java)

    @InjectMocks
    val userCreatorService = UserCreatorService(userRepo)

    @Test
    fun createOk(){
        val id = UUID.randomUUID()
        val pseudo = "macaron"
        val userResult = User(id, pseudo, 4, deck)
        `when`(userRepo.create(AddUser(pseudo, 4))).thenReturn(userResult)
        Assertions.assertTrue(userCreatorService.createUser(pseudo) == userResult)
    }
}