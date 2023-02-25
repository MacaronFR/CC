package adapters.heroes

import entities.Heroes
import org.ktorm.entity.Entity
import types.Rarity
import types.Specialty
import java.util.*

internal interface KtormHero: Entity<KtormHero> {
    var id: UUID
    var name: String
    var healthPoints: Int
    var power: Int
    var armor: Int
    var specialty: Specialty
    var rarity: Rarity

    fun toHeroes(): Heroes = Heroes(id, name, healthPoints, power, armor, specialty, rarity)

    companion object: Entity.Factory<KtormHero>(){
        fun fromHeroes(value: Heroes): KtormHero = KtormHero {
            id = value.id
            name = value.name
            healthPoints = value.healthPoints
            power = value.power
            armor = value.armor
            specialty = value.specialty
            rarity = value.rarity
        }
    }
}