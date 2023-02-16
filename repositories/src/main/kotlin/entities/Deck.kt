package entities

import kotlinx.serialization.Serializable
import serializer.UUIDSerializer
import java.util.*

@Serializable
data class Deck(
		@Serializable(with = UUIDSerializer::class)
		val id: UUID
)
