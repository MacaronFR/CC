package entities.users

import entities.deck.Deck
import java.util.*

data class User (
		val id: UUID,
		val pseudo: String,
		val token: Int,
		val deck: Deck
)
