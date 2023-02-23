package ports

import entities.Users
import java.util.*

interface UsersRepository{
	fun read(id: UUID): Nothing

	fun readAll(): List<Nothing>

	fun create(value: Nothing): Nothing

	fun update(id: UUID, value: Nothing): Nothing

	fun delete(id: UUID): Nothing
}