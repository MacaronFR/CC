package ports.out

import entities.Deck

interface DeckAppender {

	fun append(deck: Deck, card: Any)

}