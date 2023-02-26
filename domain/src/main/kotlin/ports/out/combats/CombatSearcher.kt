package ports.out.combats

import entities.combats.Combat
import java.util.UUID

interface CombatSearcher {
	fun getAll(): List<Combat>
	fun searchCombatByHero(hero: UUID): List<Combat>

	fun searchCombatByUser(user: UUID): List<Combat>
}