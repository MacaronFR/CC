package ports.out.users

import entities.users.UpdateUser
import entities.users.User
import java.util.UUID

interface UserUpdater {
	fun update(id: UUID, data: UpdateUser): User
}