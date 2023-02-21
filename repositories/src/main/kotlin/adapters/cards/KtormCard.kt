package adapters.cards

import adapters.decks.KtormDeck
import adapters.heroes.KtormHero
import entities.card.Card
import org.ktorm.entity.Entity
import java.util.UUID

internal interface KtormCard: Entity<KtormCard> {

	var id: UUID
	var deck: KtormDeck
	var hero: KtormHero

	fun toCard(): Card = Card(id, hero.toHeroes())

	companion object: Entity.Factory<KtormCard>(){
		fun fromCard(card: Card, deck: KtormDeck) = KtormCard{
			id = card.id
			this.deck = deck
			hero = KtormHero.fromHeroes(card.hero)
		}
	}
}