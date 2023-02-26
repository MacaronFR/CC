package ports.out.deck

import entities.card.Card
import entities.deck.Deck

interface DeckAppender {

	fun append(deck: Deck, card: Card): Deck

}