package ports.out.combats

import entities.combats.Combat
import java.util.UUID

interface CombatSearcher {
	fun searchCombatByHero(hero: UUID): List<Combat>

	fun searchCombatByUser(user: UUID): List<Combat>
}