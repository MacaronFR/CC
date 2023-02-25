package ports.out.users

import entities.users.User
import java.util.UUID

interface UserSearch {
	fun getAll(): List<User>

	fun searchByPseudo(pseudo: String): List<User>

	fun getById(id: UUID): User
}