package ports.out.combats

import entities.Heroes
import entities.combats.Combat
import entities.users.User

interface CombatSearcher {
	fun searchCombatByHero(hero: Heroes): List<Combat>

	fun searchCombatByUser(user: User): List<User>
}