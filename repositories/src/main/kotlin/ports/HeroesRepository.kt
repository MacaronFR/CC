package ports

import entities.heroes.Hero
import types.Rarity
import java.util.*

interface HeroesRepository{
	fun read(id: UUID): Hero

	fun readAll(): List<Hero>

	fun randomByRarity(rarity: Rarity): Hero

	fun create(value: Hero): Hero
}