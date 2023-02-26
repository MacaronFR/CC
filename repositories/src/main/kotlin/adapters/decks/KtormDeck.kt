package adapters.decks

import entities.card.Card
import entities.deck.Deck
import org.ktorm.entity.Entity
import java.util.UUID

internal interface KtormDeck: Entity<KtormDeck> {
    var id: UUID
    var name: String

    fun toDeck(cards: List<Card>): Deck = Deck(id, name, cards)

    companion object: Entity.Factory<KtormDeck>(){
        fun fromDeck(value: Deck): KtormDeck = KtormDeck {
            id = value.id
            name = value.name
        }
    }
}