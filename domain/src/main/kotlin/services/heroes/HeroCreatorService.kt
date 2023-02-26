package services.heroes

import entities.Heroes
import ports.HeroesRepository
import ports.out.heroes.HeroCreator

class HeroCreatorService(private val heroRepo: HeroesRepository): HeroCreator {
	override fun create(hero: Heroes): Heroes = heroRepo.create(hero)
}