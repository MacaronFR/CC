package adapters.cards

import adapters.heroes.Heroes
import org.ktorm.schema.Table
import org.ktorm.schema.uuid

internal object Cards: Table<KtormCard>("CARDS") {
	val id = uuid("id").primaryKey()
	val deck = uuid("deck")
	val hero = uuid("hero").references(Heroes) { it.hero }
}