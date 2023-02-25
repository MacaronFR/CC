package services.users

import entities.users.UpdateUser
import entities.users.User
import ports.UsersRepository
import ports.out.users.UserUpdater
import java.util.*

class UserUpdaterService(private val userRepo: UsersRepository): UserUpdater {
	override fun update(id: UUID, data: UpdateUser): User = userRepo.update(id, data)
}