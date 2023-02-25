package adapters.combats

import adapters.cards.Cards
import adapters.users.Users
import org.ktorm.schema.Table
import org.ktorm.schema.uuid

internal object Combats: Table<KtormCombat>("COMBATS") {
	val id = uuid("id").primaryKey().bindTo { it.id }
	val user1 = uuid("user1").references(Users) { it.user1 }
	val hero1 = uuid("hero1").references(Cards) { it.card1 }
	val user2 = uuid("user1").references(Users) { it.user2 }
	val hero2 = uuid("hero1").references(Cards) { it.card2 }
	val winner = uuid("winner").references(Users) { it.winner }
}