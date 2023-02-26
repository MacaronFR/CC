package types

import kotlinx.serialization.Serializable

@Serializable
data class CreateHero(
		val name: String,
		@Serializable(with = SpecialitySerializer::class)
		val speciality: Speciality,
		@Serializable(with = RaritySerializer::class)
		val rarity: Rarity
)
