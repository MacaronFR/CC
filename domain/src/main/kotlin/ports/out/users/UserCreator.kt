package ports.out.users

import entities.users.User

interface UserCreator {

	val defaultToken: Int

	fun createUser(pseudo: String): User
}