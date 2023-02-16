package adapters.decks

import adapters.decks
import entities.Deck
import ports.DecksRepository
import java.util.*
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.map

class KtormDecksRepository(private val db: Database): DecksRepository {
	override fun read(id: UUID): Deck = db.decks.find { it.id eq id }?.toDeck() ?: throw DeckNotFoundException()

	override fun readAll(): List<Deck> = db.decks.map { it.toDeck() }

	override fun create(value: Deck): Deck {
		db.decks.add(KtormDeck.fromDeck(value))
		return read(value.id)
	}

	override fun update(id: UUID): Deck = db.decks.find { it.id eq id }?.run {

		toDeck()
	} ?: throw DeckNotFoundException()

	override fun delete(id: UUID): Deck = db.decks.find { it.id eq id }?.run {
		delete()
		toDeck()
	} ?: throw DeckNotFoundException()
}

