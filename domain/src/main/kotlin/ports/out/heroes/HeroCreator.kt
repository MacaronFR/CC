package ports.out.heroes

import entities.heroes.Hero

interface HeroCreator {
	fun create(hero: Hero): Hero
}