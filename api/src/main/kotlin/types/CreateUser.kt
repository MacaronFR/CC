package types

import kotlinx.serialization.Serializable

@Serializable
data class CreateUser(
		val pseudo: String
)
