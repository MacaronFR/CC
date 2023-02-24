package entities.users

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUser(
		val pseudo: String? = null
)
