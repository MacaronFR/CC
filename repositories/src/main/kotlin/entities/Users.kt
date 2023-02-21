package entities

import java.util.*

data class Users (
    val id: UUID,
    val pseudo: String,
    val jeton: Int
)
