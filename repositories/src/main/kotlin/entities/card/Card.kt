package entities.card

import entities.Heroes
import kotlinx.serialization.Serializable
import serializer.UUIDSerializer
import java.util.*

@Serializable
data class Card(
		@Serializable(with = UUIDSerializer::class)
		val id: UUID,
		val hero: Heroes
)
