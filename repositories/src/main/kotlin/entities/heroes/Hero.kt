package entities.heroes

import kotlinx.serialization.Serializable
import serializer.UUIDSerializer
import types.Rarity
import types.RaritySerializer
import types.SpecialitySerializer
import types.Speciality
import java.util.*

@Serializable
data class Hero(
		@Serializable(with = UUIDSerializer::class)
		var id: UUID,
		var name: String,
		var healthPoints: Int,
		var power: Int,
		var armor: Int,
		@Serializable(with = SpecialitySerializer::class)
		var specialty: Speciality,
		@Serializable(with = RaritySerializer::class)
		var rarity: Rarity,
) {
	class Factory {
		companion object {
			operator fun invoke(name: String, specialty: Speciality, rarity: Rarity): Hero {
				val id = UUID.randomUUID()
				val baseStats = calculateStatDeBase(specialty)

				return Hero(
						id,
						name,
						baseStats["healthPoints"]!!,
						baseStats["power"]!!,
						baseStats["armor"]!!,
						specialty,
						rarity,
				)
			}

			private fun calculateStatDeBase(specialty: Speciality): Map<String, Int> = when(specialty) {
				Speciality.TANK -> mapOf("healthPoints" to 1000, "power" to 100, "armor" to 20)
				Speciality.ASSASSIN -> mapOf("healthPoints" to 800, "power" to 200, "armor" to 5)
				Speciality.MAGE -> mapOf("healthPoints" to 700, "power" to 150, "armor" to 10)
			}
		}
	}
}
