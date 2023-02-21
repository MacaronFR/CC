package services.decks

import entities.deck.Deck
import ports.DecksRepository
import ports.out.DeckAppender

class DeckAppenderService(private val repository: DecksRepository): DeckAppender {
	override fun append(deck: Deck, card: Any) {
		TODO("Not yet implemented")
	}

}