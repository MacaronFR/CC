
import adapters.users.UserNotFoundException
import entities.deck.Deck
import entities.users.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import ports.UsersRepository
import services.users.UserSearchService
import java.util.*

class UserSearchServiceTest {

    companion object {
        @JvmStatic
        @BeforeAll
        fun init() {
            MockitoAnnotations.openMocks(this)
        }
    }

    val userRepo = mock(UsersRepository::class.java)

    @InjectMocks
    val userSearchService: UserSearchService = UserSearchService(userRepo)
    val id = UUID.randomUUID()
    val pseudo = "macaron"
    val deck: Deck = mock(Deck::class.java)
    val userResult = listOf(User(id, pseudo, 4, deck))
    @Test
    fun getAllTest(){
        `when`(userRepo.readAll()).thenReturn(userResult)
        Assertions.assertTrue(userSearchService.getAll() == userResult)
    }

    @Test
    fun getOk(){
        `when`(userRepo.read(id)).thenReturn(userResult[0])
        Assertions.assertTrue(userSearchService.getById(id) == userResult[0])
    }

    @Test
    fun getNotOk(){
        val badId = UUID.randomUUID()
        doAnswer { throw UserNotFoundException() }.`when`(userRepo).read(badId)
        assertThrows<UserNotFoundException> {
            userSearchService.getById(badId)
        }
    }

    @Test
    fun getByPseudo(){
        `when`(userRepo.readByPseudo(pseudo)).thenReturn(userResult.filter { it.pseudo == pseudo })
        Assertions.assertTrue(userSearchService.searchByPseudo(pseudo) == userResult.filter { it.pseudo == pseudo })
    }
}