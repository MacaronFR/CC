package services.packs

import entities.card.Card
import entities.users.User
import ports.CardsRepository
import ports.HeroesRepository
import ports.out.exception.NoTokenLeftException
import ports.out.packs.PackOpener
import types.PackType

class PackOpenerService(private val cardRepo: CardsRepository, private val heroRepo: HeroesRepository): PackOpener {
	override fun open(user: User, type: PackType): List<Card> {
		if(!user.canOpen(type)){
			throw NoTokenLeftException()
		}
		return openPack(type).onEach {
			cardRepo.create(it, user.deck.id)
		}
	}

	private fun User.canOpen(type: PackType): Boolean = when(type){
		PackType.SILVER -> token >= 1
		PackType.DIAMOND -> token >= 2
	}

	private fun openPack(type: PackType): List<Card> {
		val nCard = when(type){
			PackType.SILVER -> 3
			PackType.DIAMOND -> 5
		}
		val cards = mutableListOf<Card>()
		repeat(nCard) {
			cards.add(Card.Factory(type, heroRepo))
		}
		return cards
	}
}