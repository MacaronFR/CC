package entities.combats

import entities.Heroes
import entities.users.User
import kotlinx.serialization.Serializable
import serializer.UUIDSerializer
import java.util.UUID

@Serializable
data class Combat(
		@Serializable(UUIDSerializer::class)
		val id: UUID,
		val user1: User,
		val hero1: Heroes,
		val user2: User,
		val hero2: Heroes,
		val winner: User
)
