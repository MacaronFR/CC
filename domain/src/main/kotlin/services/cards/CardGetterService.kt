package services.cards

import entities.card.Card
import ports.CardsRepository
import ports.out.cards.CardGetter
import java.util.*

class CardGetterService(private val cardRepo: CardsRepository): CardGetter {
	override fun get(id: UUID): Card = cardRepo.read(id)
}