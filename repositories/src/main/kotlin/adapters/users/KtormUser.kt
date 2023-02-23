package adapters.users

import adapters.decks.KtormDeck
import entities.Users
import org.ktorm.entity.Entity
import java.util.UUID

internal interface KtormUser: Entity<KtormUser> {
	var id: UUID
	var pseudo: String
	var token: Int
	var deck: KtormDeck

	fun toUser(): Users = Users(id, pseudo, token, deck.toDeck())

	companion object: Entity.Factory<KtormUser>(){
		fun fromUser(user: Users): KtormUser = KtormUser{
			id = user.id
			pseudo = user.pseudo
			token = user.token
			deck = KtormDeck.fromDeck(user.deck)
		}
	}
}