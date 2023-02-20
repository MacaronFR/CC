package domain.services

import entities.Heroes
import entities.Rarity
import entities.Specialty

class HeroService {

    fun calculatePower(hero: Heroes, opponent: Heroes?): Int {
        var power = hero.power

        if (hero.specialty == Specialty.TANK && opponent?.specialty == Specialty.MAGE) {
            power += 20
        } else if (hero.specialty == Specialty.ASSASSIN && opponent?.specialty == Specialty.TANK) {
            power += 30
        } else if (hero.specialty == Specialty.MAGE && opponent?.specialty == Specialty.ASSASSIN) {
            power += 25
        }

        return power
    }

    fun calculateArmor(hero: Heroes): Int {
        val baseArmor = hero.armor
        val rarityMultiplier = when (hero.rarity) {
            Rarity.COMMON -> 1.0
            Rarity.RARE -> 1.1
            Rarity.LEGENDARY -> 1.2
        }

        return (baseArmor * rarityMultiplier).toInt()
    }

    fun calculateBaseStats(hero: Heroes): Map<String, Int> {
        val baseHealthPoints = hero.healthPoints
        val basePower = hero.power
        val baseArmor = hero.armor
        val rarityMultiplier = when (hero.rarity) {
            Rarity.COMMON -> 1.0
            Rarity.RARE -> 1.1
            Rarity.LEGENDARY -> 1.2
        }
        val levelMultiplier = 1 + 0.1 * (hero.level - 1)

        val healthPoints = (baseHealthPoints * rarityMultiplier * levelMultiplier).toInt()
        val power = (basePower * rarityMultiplier * levelMultiplier).toInt()
        val armor = (baseArmor * rarityMultiplier * levelMultiplier).toInt()

        return mapOf(
            "healthPoints" to healthPoints,
            "power" to power,
            "armor" to armor
        )
    }

    fun manageExperience(hero: Heroes) {
        hero.experiencePoints += 1

        if (hero.experiencePoints >= 5) {
            hero.experiencePoints = 0
            hero.level += 1

            val baseStats = calculateBaseStats(hero)
            hero.healthPoints = baseStats["healthPoints"]!!
            hero.power = baseStats["power"]!!
            hero.armor = baseStats["armor"]!!
        }
    }
}
