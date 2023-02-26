package services.heroes

import entities.heroes.Hero

internal object HeroService {
	fun fight(hero: Hero, opponent: Hero): Hero = hero.copy(healthPoints = hero.healthPoints - (opponent.power - hero.armor) )
}