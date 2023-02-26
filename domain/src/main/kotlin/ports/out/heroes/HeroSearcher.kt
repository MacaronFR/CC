package ports.out.heroes

import entities.Heroes
import java.util.UUID

interface HeroSearcher {
	fun getAll(): List<Heroes>

	fun get(id: UUID): Heroes
}