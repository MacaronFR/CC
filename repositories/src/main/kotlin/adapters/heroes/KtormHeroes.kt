package adapters.heroes

import entities.Heroes
import org.ktorm.entity.Entity
import java.util.UUID

internal interface KtormHeroes: Entity<KtormHeroes> {
    val id: UUID
    val name: String
    var healthPoints: Int
    var experiencePoints: Int
    val power: Int
    val armor: Int
    val specialty: Specialty
    val rarity: Rarity
    var level: Int

    fun toHeroes(): Heroes = Heroes(id, name, healthPoints, experiencePoints, power, armor, specialty, rarity, level)

    companion object: Entity.Factory<KtormHeroes>(){
        fun fromHeroes(value: Heroes): KtormHeroes = KtormHeroes {
            id = value.id
            name = value.name
            health
        }
    }
}