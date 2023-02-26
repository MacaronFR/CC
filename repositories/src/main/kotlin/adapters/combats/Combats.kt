package adapters.combats

import adapters.cards.Cards
import adapters.users.Users
import org.ktorm.schema.Table
import org.ktorm.schema.uuid

internal object Combats: Table<KtormCombat>("COMBATS") {
	val id = uuid("id").primaryKey().bindTo { it.id }
	val user1 = uuid("user1").references(Users) { it.user1 }
	val card1 = uuid("card1").references(Cards) { it.card1 }
	val user2 = uuid("user2").references(Users) { it.user2 }
	val card2 = uuid("card2").references(Cards) { it.card2 }
	val winner = uuid("winner").references(Users) { it.winner }
}