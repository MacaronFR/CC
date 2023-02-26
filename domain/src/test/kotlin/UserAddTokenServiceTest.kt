import entities.combats.Combat
import entities.users.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import ports.CombatRepository
import ports.UsersRepository
import services.users.UserAddTokenService
import java.util.UUID

class UserAddTokenServiceTest {
	companion object {
		@JvmStatic
		@BeforeAll
		fun init() {
			MockitoAnnotations.openMocks(this)
		}
	}

	val userRepo: UsersRepository = mock(UsersRepository::class.java)

	val combatRepo: CombatRepository = mock(CombatRepository::class.java)

	val combat: Combat = mock(Combat::class.java)

	val user: User = mock(User::class.java)

	@InjectMocks
	val userAddTokenService = UserAddTokenService(userRepo, combatRepo)

	@Test
	fun canAddTokenOk(){
		val id = UUID.randomUUID()
		`when`(combatRepo.readByWinner(id)).thenReturn(listOf(combat, combat, combat, combat, combat))
		`when`(user.id).thenReturn(id)
		Assertions.assertTrue(userAddTokenService.canAddToken(user))
	}

	@Test
	fun canAddTokenNotOk(){
		val id = UUID.randomUUID()
		`when`(combatRepo.readByWinner(id)).thenReturn(listOf(combat, combat, combat))
		`when`(user.id).thenReturn(id)
		Assertions.assertFalse(userAddTokenService.canAddToken(user))
	}
}