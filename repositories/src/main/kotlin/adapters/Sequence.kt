package adapters

import adapters.cards.Cards
import adapters.decks.Decks
import adapters.heroes.Heroes
import adapters.users.Users
import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf

internal val Database.decks get() = this.sequenceOf(Decks)
internal val Database.heroes get() = this.sequenceOf(Heroes)
internal val Database.cards get() = this.sequenceOf(Cards)
internal val Database.users get() = this.sequenceOf(Users)