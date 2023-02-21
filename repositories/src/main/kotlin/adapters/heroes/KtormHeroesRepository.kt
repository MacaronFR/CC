package adapters.heroes

import adapters.heroes
import entities.Heroes
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.map
import ports.HeroesRepository
import java.util.*

class KtormHeroesRepository(private val db: Database): HeroesRepository{
    override fun read(id: UUID): Heroes = db.heroes.find { it.id eq id }?.toHeroes() ?: throw HeroNotFoundException()


    override fun readAll(): List<Heroes> = db.heroes.map { it.toHeroes() }

    override fun create(value: Heroes): Heroes {
        val newHero = Heroes.HeroesFactory().create(value.name, value.specialty, value.rarity)
        db.heroes.add() {
            set(it.id, newHero.id)
            set(it.name, newHero.name)
            set(it.specialty, newHero.specialty)
            set(it.rarity, newHero.rarity)
            set(it.level, newHero.level)
            set(it.experiencePoints, newHero.experiencePoints)
            set(it.healthPoints, newHero.healthPoints)
            set(it.power, newHero.power)
            set(it.armor, newHero.armor)
        }
        return newHero
    }

    override fun update(id: UUID, value: Nothing): Heroes {
        TODO("Not yet implemented")
    }

    override fun delete(id: UUID): Heroes {
        TODO("Not yet implemented")
    }

}



