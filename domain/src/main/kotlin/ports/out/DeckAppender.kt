package ports.out

import entities.deck.Deck

interface DeckAppender {

	fun append(deck: Deck, card: Any)

}