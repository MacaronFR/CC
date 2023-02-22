package ports.out.deck

import entities.deck.Deck
import java.util.UUID

interface DeckGetter {
	fun get(id: UUID): Deck

	fun getAll(): List<Deck>
}