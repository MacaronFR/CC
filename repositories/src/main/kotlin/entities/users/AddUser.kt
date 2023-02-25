package entities.users

import kotlinx.serialization.Serializable

@Serializable
data class AddUser(
		val pseudo: String,
		val token: Int
)
