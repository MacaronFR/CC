package services.users

import entities.users.User
import ports.CombatRepository
import ports.UsersRepository
import ports.out.users.UserAddToken

class UserAddTokenService(
		private val userRepo: UsersRepository,
		private val combatRepo: CombatRepository
): UserAddToken {
	override val toAddToken: Int = 1

	override fun addToken(user: User) {
		userRepo.addToken(user.id, toAddToken)
	}

	override fun canAddToken(user: User): Boolean = combatRepo.readByWinner(user.id).count() % 5 == 0
}