package ports.out.heroes

import entities.users.User
import java.util.UUID

interface HeroSearcher {
	fun getAll(): List<User>

	fun get(id: UUID): User

	fun getByPseudo(pseudo: String): List<String>
}