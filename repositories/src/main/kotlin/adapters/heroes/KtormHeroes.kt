package adapters.heroes

import entities.Heroes
import entities.Rarity
import entities.Specialty
import org.ktorm.entity.Entity
import java.util.*

internal interface KtormHeroes: Entity<KtormHeroes> {
    var id: UUID
    var name: String
    var healthPoints: Int
    var experiencePoints: Int
    var power: Int
    var armor: Int
    var specialty: Specialty
    var rarity: Rarity
    var level: Int

    fun toHeroes(): Heroes = Heroes(id, name, healthPoints, experiencePoints, power, armor, specialty, rarity, level)

    companion object: Entity.Factory<KtormHeroes>(){
        fun fromHeroes(value: Heroes): KtormHeroes = KtormHeroes {
            id = value.id
            name = value.name
            healthPoints = value.healthPoints
            experiencePoints = value.experiencePoints
            power = value.power
            armor = value.armor
            specialty = value.specialty
            rarity = value.rarity
            level = value.level
        }
    }
}