package entities.deck

import kotlinx.serialization.Serializable

@Serializable
data class DeckUpdate(
		val name: String? = null
)
