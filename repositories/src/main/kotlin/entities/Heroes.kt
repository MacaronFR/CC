package entities

import kotlinx.serialization.Serializable
import serializer.UUIDSerializer
import types.Rarity
import types.RaritySerializer
import types.SpecialitySerializer
import types.Specialty
import java.util.*

@Serializable
data class Heroes(
		@Serializable(with = UUIDSerializer::class)
		var id: UUID,
		var name: String,
		var healthPoints: Int,
		var power: Int,
		var armor: Int,
		@Serializable(with = SpecialitySerializer::class)
		var specialty: Specialty,
		@Serializable(with = RaritySerializer::class)
		var rarity: Rarity,
) {
	class HeroesFactory {
		fun create(name: String, specialty: Specialty, rarity: Rarity): Heroes {
			val id = UUID.randomUUID()
			val baseStats = calculateStatDeBase(specialty, rarity)

			return Heroes(
				id,
				name,
				baseStats["healthPoints"]!!,
				baseStats["power"]!!,
				baseStats["armor"]!!,
				specialty,
				rarity,
			)
		}

		private fun calculateStatDeBase(specialty: Specialty, rarity: Rarity): Map<String, Int> {
			// calcul des stats en fonction de la specialite et de la rrete
			return mapOf(
				"healthPoints" to 100,
				"power" to 50,
				"armor" to 20
			)
		}
	}
}
