package services.heroes

import entities.heroes.Hero
import ports.HeroesRepository
import ports.out.heroes.HeroSearcher
import java.util.*

class HeroSearcher(private val heroRepo: HeroesRepository): HeroSearcher {
	override fun getAll(): List<Hero> = heroRepo.readAll()

	override fun get(id: UUID): Hero = heroRepo.read(id)
}