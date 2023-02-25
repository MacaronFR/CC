package services.users

import entities.users.AddUser
import entities.users.User
import ports.UsersRepository
import ports.out.users.UserCreator

class UserCreatorService(private val userRepo: UsersRepository): UserCreator {
	override val defaultToken: Int = 4

	override fun createUser(pseudo: String): User = userRepo.create(AddUser(pseudo, defaultToken))
}