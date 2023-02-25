package entities.card

import entities.Heroes
import kotlinx.serialization.Serializable
import serializer.UUIDSerializer
import java.util.*

@Serializable
data class Card(
		@Serializable(with = UUIDSerializer::class)
		var id: UUID,
		var hero: Heroes,
		var experiencePoints: Int,
		var level: Int,
)
