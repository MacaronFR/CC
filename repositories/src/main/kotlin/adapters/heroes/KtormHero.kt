package adapters.heroes

import entities.heroes.Hero
import org.ktorm.entity.Entity
import types.Rarity
import types.Speciality
import java.util.*

internal interface KtormHero: Entity<KtormHero> {
    var id: UUID
    var name: String
    var healthPoints: Int
    var power: Int
    var armor: Int
    var specialty: Speciality
    var rarity: Rarity

    fun toHeroes(): Hero = Hero(id, name, healthPoints, power, armor, specialty, rarity)

    companion object: Entity.Factory<KtormHero>(){
        fun fromHeroes(value: Hero): KtormHero = KtormHero {
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