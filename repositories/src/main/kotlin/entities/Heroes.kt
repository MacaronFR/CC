package entities

import kotlinx.serialization.Serializable
import serializer.UUIDSerializer
import java.util.*

@Serializable
data class Heroes(
		@Serializable(with = UUIDSerializer::class)
		var id: UUID,
		var name: String,
		var healthPoints: Int,
		var experiencePoints: Int,
		var power: Int,
		var armor: Int,
		@Serializable(with = SpecialitySerializer::class)
		var specialty: Specialty,
		@Serializable(with = RaritySerializer::class)
		var rarity: Rarity,
		var level: Int
)
