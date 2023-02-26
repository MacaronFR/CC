package ports.out.heroes

import entities.heroes.Hero
import java.util.UUID

interface HeroSearcher {
	fun getAll(): List<Hero>

	fun get(id: UUID): Hero
}