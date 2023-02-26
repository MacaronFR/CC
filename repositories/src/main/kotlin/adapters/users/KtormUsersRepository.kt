package adapters.users

import adapters.decks.KtormDeck
import adapters.users
import entities.deck.Deck
import entities.users.AddUser
import entities.users.UpdateUser
import entities.users.User
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.dsl.like
import org.ktorm.entity.add
import org.ktorm.entity.filter
import org.ktorm.entity.find
import org.ktorm.entity.map
import ports.CardsRepository
import ports.DecksRepository
import ports.UsersRepository
import java.util.*

class KtormUsersRepository(
		private val db: Database,
		private val deckRepo: DecksRepository,
		private val cardRepo: CardsRepository
): UsersRepository {
	override fun read(id: UUID): User {
		val user = db.users.find { it.id eq id } ?: throw UserNotFoundException()
		val cards = cardRepo.getByDeck(user.deck.id)
		return user.toUser(cards)
	}

	override fun readAll(): List<User> = db.users.map { user ->
		user.toUser(cardRepo.getByDeck(user.deck.id))
	}

	override fun readByPseudo(pseudo: String): List<User> = db.users.filter { it.pseudo like "%$pseudo%" }.map { it.toUser(cardRepo.getByDeck(it.deck.id)) }

	override fun create(value: AddUser): User {
		val deck = deckRepo.create(Deck(UUID.randomUUID(), "${value.pseudo}DefaultDeck", listOf()))
		val user = KtormUser {
			id = UUID.randomUUID()
			pseudo = value.pseudo
			token = value.token
			this.deck = KtormDeck.fromDeck(deck)
		}
		db.users.add(user)
		return user.toUser(cardRepo.getByDeck(deck.id))
	}

	override fun update(id: UUID, value: UpdateUser): User = db.users.find { it.id eq id }?.run {
		value.pseudo?.let { pseudo = it }
		val cards = cardRepo.getByDeck(deck.id)
		toUser(cards)
	} ?: throw UserNotFoundException()

	override fun removeToken(id: UUID, amount: Int): User = db.users.find { it.id eq id }?.run {
		token -= amount
		flushChanges()
		toUser(cardRepo.getByDeck(deck.id))
	} ?: throw UserNotFoundException()

	override fun addToken(id: UUID, amount: Int): User = db.users.find { it.id eq id }?.run {
		token += amount
		flushChanges()
		toUser(cardRepo.getByDeck(deck.id))
	} ?: throw UserNotFoundException()

	override fun delete(id: UUID): User = db.users.find { it.id eq id }?.run {
		val cards = cardRepo.getByDeck(deck.id)
		delete()
		toUser(cards)
	} ?: throw UserNotFoundException()
}