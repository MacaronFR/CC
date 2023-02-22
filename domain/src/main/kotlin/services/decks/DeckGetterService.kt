package services.decks

import entities.deck.Deck
import ports.DecksRepository
import ports.out.deck.DeckGetter
import java.util.*

class DeckGetterService(private val repository: DecksRepository): DeckGetter {
	override fun get(id: UUID): Deck = repository.read(id)

	override fun getAll(): List<Deck> = repository.readAll()
}