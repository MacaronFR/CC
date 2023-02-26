package ports.out.users

import entities.users.User

interface UserRemoveToken {
	fun removeToken(user: User, amount: Int)
}