package ports.out.packs

import entities.card.Card
import entities.users.User
import types.PackType

interface PackOpener {
	fun open(user: User, type: PackType): List<Card>
}