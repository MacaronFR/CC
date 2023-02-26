package ports

import entities.Heroes
import types.Rarity
import java.util.*

interface HeroesRepository{
	fun read(id: UUID): Heroes

	fun readAll(): List<Heroes>

	fun randomByRarity(rarity: Rarity): Heroes

	fun create(value: Heroes): Heroes

	fun update(id: UUID, value: Nothing): Heroes

	fun delete(id: UUID): Heroes
}