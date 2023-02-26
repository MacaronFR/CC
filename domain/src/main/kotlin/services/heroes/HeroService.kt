package services.heroes

import entities.heroes.Hero
import types.Rarity
import types.Specialty

internal object HeroService {
	fun calculateBasePower(hero: Hero, opponent: Hero): Int {
		var power = hero.power

		if (hero.specialty == Specialty.TANK && opponent.specialty == Specialty.MAGE) {
			power += 20
		} else if (hero.specialty == Specialty.ASSASSIN && opponent.specialty == Specialty.TANK) {
			power += 30
		} else if (hero.specialty == Specialty.MAGE && opponent.specialty == Specialty.ASSASSIN) {
			power += 25
		}

		return power
	}

	fun calculateBaseArmor(hero: Hero): Int {
		val baseArmor = hero.armor
		val rarityMultiplier = when (hero.rarity) {
			Rarity.COMMON -> 1.0
			Rarity.RARE -> 1.1
			Rarity.LEGENDARY -> 1.2
		}

		return (baseArmor * rarityMultiplier).toInt()
	}

	fun fight(hero: Hero, opponent: Hero): Hero = hero.copy(healthPoints = hero.healthPoints - (opponent.power - hero.armor) )
}