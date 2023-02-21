package ports

import entities.deck.Deck
import entities.deck.DeckUpdate
import java.util.*

interface DecksRepository{
	fun read(id: UUID): Deck

	fun readAll(): List<Deck>

	fun create(value: Deck): Deck

	fun update(id: UUID, value: DeckUpdate): Deck

	fun delete(id: UUID): Deck
}