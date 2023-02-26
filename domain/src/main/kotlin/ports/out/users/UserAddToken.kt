package ports.out.users

import entities.users.User

interface UserAddToken {

	val toAddToken: Int

	fun addToken(user: User)

	fun canAddToken(user: User): Boolean
}