package ports.out.heroes

import entities.Heroes

interface HeroCreator {
	fun create(hero: Heroes): Heroes
}