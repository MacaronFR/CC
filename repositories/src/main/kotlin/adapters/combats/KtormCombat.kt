package adapters.combats

import adapters.cards.KtormCard
import adapters.decks.KtormDeck
import adapters.users.KtormUser
import entities.card.Card
import entities.combats.Combat
import org.ktorm.entity.Entity
import java.util.UUID

internal interface KtormCombat: Entity<KtormCombat> {
	var id: UUID
	var user1: KtormUser
	var card1: KtormCard
	var user2: KtormUser
	var card2: KtormCard
	var winner: KtormUser

	fun toCombat(card1: List<Card>, card2: List<Card>, cardWinner: List<Card>): Combat = Combat(
			id,
			user1.toUser(card1),
			this.card1.toCard(),
			user2.toUser(card2),
			this.card2.toCard(),
			winner.toUser(cardWinner)
	)

	companion object: Entity.Factory<KtormCombat>() {
		fun fromCombat(combat: Combat): KtormCombat = KtormCombat {
			id = combat.id
			user1 = KtormUser.fromUser(combat.user1)
			card1 = KtormCard.fromCard(combat.card1, KtormDeck.fromDeck(combat.user1.deck))
			user2 = KtormUser.fromUser(combat.user2)
			card2 = KtormCard.fromCard(combat.card2, KtormDeck.fromDeck(combat.user2.deck))
			winner = KtormUser.fromUser(combat.winner)
		}
	}
}