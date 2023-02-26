package adapters.heroes

import adapters.heroes
import entities.heroes.Hero
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.filter
import org.ktorm.entity.find
import org.ktorm.entity.map
import ports.HeroesRepository
import types.Rarity
import java.util.*

class KtormHeroesRepository(private val db: Database): HeroesRepository{
    override fun read(id: UUID): Hero = db.heroes.find { it.id eq id }?.toHeroes() ?: throw HeroNotFoundException()


    override fun readAll(): List<Hero> = db.heroes.map { it.toHeroes() }

    override fun randomByRarity(rarity: Rarity): Hero = db.heroes.filter { it.rarity eq rarity }.map { it }.random().toHeroes()

    override fun create(value: Hero): Hero {
        val newHero = Hero.Factory(value.name, value.specialty, value.rarity)
        db.heroes.add(KtormHero.fromHeroes(newHero))
        return newHero
    }
}



