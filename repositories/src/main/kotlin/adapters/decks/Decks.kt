package adapters.decks

import org.ktorm.schema.Table
import org.ktorm.schema.uuid

internal object Decks: Table<KtormDeck>("DECKS") {
    val id = uuid("id").primaryKey().bindTo { it.id }
}