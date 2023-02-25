package services.combats

import entities.combats.Combat
import ports.CombatRepository
import ports.out.combats.CombatSearcher
import java.util.*

class CombatSearcherService(private val combatRepo: CombatRepository): CombatSearcher {
	override fun searchCombatByHero(hero: UUID): List<Combat> = combatRepo.readByHero(hero)

	override fun searchCombatByUser(user: UUID): List<Combat> = combatRepo.readByUser(user)

}