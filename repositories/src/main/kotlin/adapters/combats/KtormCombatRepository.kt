package adapters.combats

import adapters.combats
import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import entities.combats.Combat
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.dsl.or
import org.ktorm.entity.add
import org.ktorm.entity.filter
import org.ktorm.entity.find
import org.ktorm.entity.map
import ports.CardsRepository
import ports.CombatRepository
import java.util.*

class KtormCombatRepository(private val db: Database, private val cardRepo: CardsRepository): CombatRepository {

	val deckCache = CacheBuilder.newBuilder().maximumSize(1000).build(CacheLoader.from { key: UUID -> cardRepo.getByDeck(key) })

	override fun readAll(): List<Combat> = db.combats.map {
		it.toCombat(
				deckCache[it.user1.deck.id],
				deckCache[it.user2.deck.id],
				deckCache[it.winner.deck.id]
		)
	}

	override fun read(id: UUID): Combat = db.combats.find { it.id eq id }?.let {
		it.toCombat(
				deckCache[it.user1.deck.id],
				deckCache[it.user2.deck.id],
				deckCache[it.winner.deck.id]
		)
	} ?: throw CombatsNotFoundException()

	override fun readByHero(id: UUID): List<Combat> = db.combats.filter { (it.hero1 eq id) or (it.hero2 eq id) }.map {
		it.toCombat(
				deckCache[it.user1.deck.id],
				deckCache[it.user2.deck.id],
				deckCache[it.winner.deck.id]
		)
	}

	override fun create(data: Combat): Combat {
		val combat = KtormCombat.fromCombat(data)
		db.combats.add(combat)
		return combat.toCombat(
				deckCache[combat.user1.deck.id],
				deckCache[combat.user2.deck.id],
				deckCache[combat.winner.deck.id]
		)
	}
}