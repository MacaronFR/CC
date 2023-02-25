package ports.out.packs

import entities.card.Card
import entities.users.User

interface PackOpener {
	fun open(user: User): List<Card>
}