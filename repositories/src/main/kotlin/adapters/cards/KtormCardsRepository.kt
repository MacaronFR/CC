package adapters.cards

import adapters.cards
import adapters.decks
import adapters.decks.DeckNotFoundException
import entities.card.Card
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.filter
import org.ktorm.entity.find
import org.ktorm.entity.map
import ports.CardsRepository
import java.util.*

class KtormCardsRepository(private val db: Database): CardsRepository {
	override fun getByDeck(deck: UUID): List<Card> = db.cards.filter { it.deck eq deck }.map { it.toCard() }

	override fun deleteByDeck(deck: UUID): List<Card> = db.cards.filter { it.deck eq deck }.map {
		it.delete()
		it.toCard()
	}

	override fun read(id: UUID): Card = db.cards.find { it.id eq id }?.toCard() ?: throw CardNotFoundException()

	override fun readAll(): List<Card> = db.cards.map { it.toCard() }

	override fun create(value: Card, deck: UUID): Card {
		val card = KtormCard.fromCard(value, db.decks.find { it.id eq deck } ?: throw DeckNotFoundException())
		db.cards.add(card)
		return card.toCard()
	}

	override fun update(id: UUID, value: Card): Card = db.cards.find { it.id eq id }?.apply {

		flushChanges()
	}?.toCard() ?: throw CardNotFoundException()

	override fun delete(id: UUID): Card = db.cards.find { it.id eq id }?.run {
		delete()
		toCard()
	} ?: throw CardNotFoundException()
}