package entities.card

import entities.Heroes
import kotlinx.serialization.Serializable
import ports.HeroesRepository
import serializer.UUIDSerializer
import types.PackType
import types.Rarity
import java.util.*
import kotlin.random.Random

@Serializable
data class Card(
		@Serializable(with = UUIDSerializer::class)
		var id: UUID,
		var hero: Heroes,
		var experiencePoints: Int,
		var level: Int,
){
	class Factory {
		companion object{
			operator fun invoke(type: PackType, heroRepo: HeroesRepository): Card {
				val rand = Random.nextInt(100)
				val rarity = when(type){
					PackType.SILVER -> generateSilver(rand)
					PackType.DIAMOND -> generateDiamond(rand)
				}
				val hero = heroRepo.randomByRarity(rarity)
				return Card(UUID.randomUUID(), hero, 0, 0)
			}

			private fun generateSilver(rand: Int): Rarity = when(rand) {
				in 0..4 -> Rarity.LEGENDARY
				in 5..24 -> Rarity.RARE
				else -> Rarity.COMMON
			}

			private fun generateDiamond(rand: Int): Rarity = when(rand) {
				in 0..14 -> Rarity.LEGENDARY
				in 15..39 -> Rarity.RARE
				else -> Rarity.COMMON
			}
		}
	}
}
