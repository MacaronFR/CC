package entities.combats

import entities.card.Card
import entities.users.User
import kotlinx.serialization.Serializable
import serializer.UUIDSerializer
import java.util.UUID

@Serializable
data class Combat(
		@Serializable(UUIDSerializer::class)
		val id: UUID,
		val user1: User,
		val card1: Card,
		val user2: User,
		val card2: Card,
		val winner: User
)
