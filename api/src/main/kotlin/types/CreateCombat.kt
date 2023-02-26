package types

import kotlinx.serialization.Serializable
import serializer.UUIDSerializer
import java.util.UUID

@Serializable
data class CreateCombat(
		@Serializable(with = UUIDSerializer::class)
		val user: UUID,
		@Serializable(with = UUIDSerializer::class)
		val card: UUID
)
