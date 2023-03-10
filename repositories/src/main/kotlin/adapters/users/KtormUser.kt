package adapters.users

import adapters.decks.KtormDeck
import entities.card.Card
import entities.users.User
import org.ktorm.entity.Entity
import java.util.UUID

internal interface KtormUser: Entity<KtormUser> {
	var id: UUID
	var pseudo: String
	var token: Int
	var deck: KtormDeck

	fun toUser(cards: List<Card>): User = User(id, pseudo, token, deck.toDeck(cards))

	companion object: Entity.Factory<KtormUser>(){
		fun fromUser(user: User): KtormUser = KtormUser{
			id = user.id
			pseudo = user.pseudo
			token = user.token
			deck = KtormDeck.fromDeck(user.deck)
		}
	}
}