package adapters.combats

import adapters.heroes.KtormHero
import adapters.users.KtormUser
import entities.card.Card
import entities.combats.Combat
import org.ktorm.entity.Entity
import java.util.UUID

internal interface KtormCombat: Entity<KtormCombat> {
	var id: UUID
	var user1: KtormUser
	var hero1: KtormHero
	var user2: KtormUser
	var hero2: KtormHero
	var winner: KtormUser

	fun toCombat(card1: List<Card>, card2: List<Card>, cardWinner: List<Card>): Combat = Combat(
			id,
			user1.toUser(card1),
			hero1.toHeroes(),
			user2.toUser(card2),
			hero2.toHeroes(),
			winner.toUser(cardWinner)
	)

	companion object: Entity.Factory<KtormCombat>() {
		fun fromCombat(combat: Combat): KtormCombat = KtormCombat {
			id = combat.id
			user1 = KtormUser.fromUser(combat.user1)
			hero1 = KtormHero.fromHeroes(combat.hero1)
			user2 = KtormUser.fromUser(combat.user2)
			hero2 = KtormHero.fromHeroes(combat.hero2)
			winner = KtormUser.fromUser(combat.winner)
		}
	}
}