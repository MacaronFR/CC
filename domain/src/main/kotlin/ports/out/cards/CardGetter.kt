package ports.out.cards

import entities.card.Card
import java.util.*

interface CardGetter {
	fun get(id: UUID): Card
}