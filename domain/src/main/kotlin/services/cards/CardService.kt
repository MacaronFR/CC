package services.cards

import entities.heroes.Hero

internal object CardService {
	fun calculateLeveledStat(level: Int, hero: Hero): Hero {
		var levelBoost = 1.0
		for( i in 1..level){
			levelBoost *= 1.1
		}
		val leveledHealthPoint = (hero.healthPoints * levelBoost).toInt()
		val leveledPower = (hero.power * levelBoost).toInt()
		val leveledArmor = (hero.armor * levelBoost).toInt()
		return hero.copy(healthPoints = leveledHealthPoint, power = leveledPower, armor = leveledArmor)
	}
}