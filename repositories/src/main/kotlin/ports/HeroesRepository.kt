package ports

import entities.Heroes
import java.util.*

interface HeroesRepository{
	fun read(id: UUID): Heroes

	fun readAll(): List<Heroes>

	fun create(value: Heroes): Heroes

	fun update(id: UUID, value: Nothing): Heroes

	fun delete(id: UUID): Heroes
}