package services.users

import entities.users.User
import ports.UsersRepository
import ports.out.users.UserSearch
import java.util.*

class UserSearchService(
		private val userRepo: UsersRepository
): UserSearch {
	override fun getAll(): List<User> = userRepo.readAll()

	override fun searchByPseudo(pseudo: String): List<User> = userRepo.readByPseudo(pseudo)

	override fun getById(id: UUID): User = userRepo.read(id)
}