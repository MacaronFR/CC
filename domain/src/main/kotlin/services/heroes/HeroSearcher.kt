package services.heroes

import entities.Heroes
import ports.HeroesRepository
import ports.out.heroes.HeroSearcher
import java.util.*

class HeroSearcher(private val heroRepo: HeroesRepository): HeroSearcher {
	override fun getAll(): List<Heroes> = heroRepo.readAll()

	override fun get(id: UUID): Heroes = heroRepo.read(id)
}