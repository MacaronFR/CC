package services.decks

import entities.card.Card
import entities.deck.Deck
import ports.DecksRepository
import ports.out.deck.DeckAppender

class DeckAppenderService(private val deckRepo: DecksRepository): DeckAppender {
	override fun append(deck: Deck, card: Card): Boolean {
		val res = deckRepo.appendCard(deck, card)
		return card in res.cards
	}

}