package entities

import java.util.*

data class Heroes(
    var id: UUID,
    var name: String,
    var healthPoints: Int,
    var experiencePoints: Int,
    var power: Int,
    var armor: Int,
    var specialty: Specialty,
    var rarity: Rarity,
    var level: Int
)
