package ports

import entities.users.AddUser
import entities.users.UpdateUser
import entities.users.User
import java.util.*

interface UsersRepository{
	fun read(id: UUID): User

	fun readAll(): List<User>

	fun readByPseudo(pseudo: String): List<User>

	fun create(value: AddUser): User

	fun update(id: UUID, value: UpdateUser): User

	fun delete(id: UUID): User
}