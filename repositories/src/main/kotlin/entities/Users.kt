package entities

import java.util.*

data class Users (
		val id: UUID,
		val pseudo: String,
		val token: Int,
		val deck: Deck
)
