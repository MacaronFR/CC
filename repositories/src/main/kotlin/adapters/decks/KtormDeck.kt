package adapters.decks

import entities.Deck
import org.ktorm.entity.Entity
import java.util.UUID

internal interface KtormDeck: Entity<KtormDeck> {
    var id: UUID

    fun toDeck(): Deck = Deck(id)

    companion object: Entity.Factory<KtormDeck>(){
        fun fromDeck(value: Deck): KtormDeck = KtormDeck {
            id = value.id
        }
    }
}