package ports.out.combats

import entities.Heroes
import entities.users.User

interface StartCombat {
	fun combat(user1: User, hero1: Heroes, user2: User, hero2: User): User
}