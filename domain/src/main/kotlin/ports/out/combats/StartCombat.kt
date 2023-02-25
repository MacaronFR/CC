package ports.out.combats

import entities.card.Card
import entities.combats.Combat
import entities.users.User

interface StartCombat {
	fun combat(user1: User, card1: Card, user2: User, card2: Card): Combat
}