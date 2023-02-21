package adapters.decks

import org.ktorm.schema.Table
import org.ktorm.schema.uuid
import org.ktorm.schema.varchar

internal object Decks: Table<KtormDeck>("DECKS") {
    val id = uuid("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
}