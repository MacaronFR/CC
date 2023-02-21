package adapters.heroes

import adapters.heroes
import entities.Heroes
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.find
import org.ktorm.entity.map
import ports.HeroesRepository
import java.util.*

class KtormHeroesRepository(private val db: Database): HeroesRepository{
    override fun read(id: UUID): Heroes = db.heroes.find { it.id eq id }?.toHeroes() ?: throw HeroNotFoundException()


    override fun readAll(): List<Heroes> = db.heroes.map { it.toHeroes() }

    override fun create(value: Heroes): Heroes {
        TODO("Not yet implemented")
    }

    override fun update(id: UUID, value: Nothing): Heroes {
        TODO("Not yet implemented")
    }

    override fun delete(id: UUID): Heroes {
        TODO("Not yet implemented")
    }

}