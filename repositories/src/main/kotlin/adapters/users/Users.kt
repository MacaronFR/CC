package adapters.users

import adapters.decks.Decks
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.uuid
import org.ktorm.schema.varchar

internal object Users: Table<KtormUser>("USERS") {
	val id = uuid("id").primaryKey().bindTo { it.id }
	val pseudo = varchar("pseudo").bindTo { it.pseudo }
	val token = int("token").bindTo { it.token }
	val deck = uuid("deck").references(Decks) { it.deck }
}