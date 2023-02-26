package services.heroes

import entities.heroes.Hero
import ports.HeroesRepository
import ports.out.heroes.HeroCreator

class HeroCreatorService(private val heroRepo: HeroesRepository): HeroCreator {
	override fun create(hero: Hero): Hero = heroRepo.create(hero)
}