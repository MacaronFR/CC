package ports

import entities.card.Card
import java.util.*

interface CardsRepository{
	fun getByDeck(deck: UUID): List<Card>

	fun deleteByDeck(deck: UUID): List<Card>

	fun read(id: UUID): Card

	fun readAll(): List<Card>

	fun create(value: Card, deck: UUID): Card

	fun update(id: UUID, value: Card): Card

	fun delete(id: UUID): Card
}