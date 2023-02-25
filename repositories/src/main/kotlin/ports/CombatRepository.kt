package ports

import entities.combats.Combat
import java.util.UUID

interface CombatRepository {
	fun readAll(): List<Combat>

	fun read(id: UUID): Combat

	fun readByHero(id: UUID): List<Combat>

	fun create(data: Combat): Combat
}