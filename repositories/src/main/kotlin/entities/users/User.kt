package entities.users

import entities.deck.Deck
import kotlinx.serialization.Serializable
import serializer.UUIDSerializer
import java.util.*

@Serializable
data class User (
		@Serializable(with = UUIDSerializer::class)
		val id: UUID,
		val pseudo: String,
		val token: Int,
		val deck: Deck
)
