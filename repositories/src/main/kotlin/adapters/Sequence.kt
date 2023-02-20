package adapters

import adapters.decks.Decks
import adapters.heroes.Heroes
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf

internal val Database.decks get() = this.sequenceOf(Decks)
internal val Database.heroes get() = this.sequenceOf(Heroes)