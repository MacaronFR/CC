package adapters.decks

import adapters.decks
import entities.deck.Deck
import entities.deck.DeckUpdate
import ports.DecksRepository
import java.util.*
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.map
import ports.CardsRepository

class KtormDecksRepository(private val db: Database, private val cardsRepository: CardsRepository): DecksRepository {
	override fun read(id: UUID): Deck = db.decks.find { it.id eq id }?.toDeck(cardsRepository.getByDeck(id)) ?: throw DeckNotFoundException()

	override fun readAll(): List<Deck> = db.decks.map { deck -> deck.toDeck(cardsRepository.getByDeck(deck.id)) }

	override fun create(value: Deck): Deck {
		db.decks.add(KtormDeck.fromDeck(value))
		return read(value.id)
	}

	override fun update(id: UUID, value: DeckUpdate): Deck = db.decks.find { it.id eq id }?.run {
		toDeck(cardsRepository.getByDeck(id))
	} ?: throw DeckNotFoundException()

	override fun delete(id: UUID): Deck = db.decks.find { it.id eq id }?.run {
		delete()
		toDeck(cardsRepository.deleteByDeck(id))
	} ?: throw DeckNotFoundException()
}

