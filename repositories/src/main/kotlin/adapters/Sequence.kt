package adapters

import adapters.decks.Decks
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf

internal val Database.decks get() = this.sequenceOf(Decks)