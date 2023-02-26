import adapters.cards.CardNotFoundException
import entities.card.Card
import entities.heroes.Hero
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import ports.CardsRepository
import services.cards.CardGetterService
import org.mockito.Mockito.*
import types.Rarity
import types.Speciality
import java.util.*

class CardGetterServiceTest {

	val cardsRepo: CardsRepository = mock(CardsRepository::class.java)

	@InjectMocks
	val cardGetterService = CardGetterService(cardsRepo)

	companion object {
		@JvmStatic
		@BeforeAll
		fun init() {
			MockitoAnnotations.openMocks(this)
		}
	}

	@Test
	fun getOK() {
		val id = UUID.randomUUID()
		val refCard = Card(id, Hero.Factory("Arthur", Speciality.TANK, Rarity.LEGENDARY), 0, 1)
		`when`(cardsRepo.read(id)).thenReturn(refCard)
		val card = cardGetterService.get(id)
		verify(cardsRepo).read(id)
		Assertions.assertTrue(card == refCard)

	}

	@Test
	fun getInvalid(){
		val id = UUID.randomUUID()
		val exception = CardNotFoundException()
		doAnswer { throw exception }.`when`(cardsRepo).read(id)
		assertThrows<CardNotFoundException> {
			cardGetterService.get(id)
		}
	}
}