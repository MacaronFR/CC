package services.combats

import entities.card.Card
import entities.combats.Combat
import entities.users.User
import ports.CardsRepository
import ports.CombatRepository
import ports.out.combats.StartCombat
import ports.out.exception.StartCombatException
import services.cards.CardService
import services.heroes.HeroService
import java.util.*

class StartCombatService(private val combatRepo: CombatRepository, private val cardRepo: CardsRepository): StartCombat {
	override fun combat(user1: User, card1: Card, user2: User, card2: Card): Combat {
		if(!user1.haveCard(card1) || !user2.haveCard(card2) ){
			throw StartCombatException("User have not hero")
		}
		if(!card1.isLowerLevelOrEqualThan(card2)){
			throw StartCombatException("Card is higher level")
		}
		var hero1 = CardService.calculateLeveledStat(card1.level, card1.hero)
		var hero2 = CardService.calculateLeveledStat(card2.level, card2.hero)
		while(hero1.healthPoints > 0 && hero2.healthPoints > 0){
			hero1 = HeroService.fight(hero1, hero2)
			hero2 = HeroService.fight(hero2, hero1)
		}
		return Combat(UUID.randomUUID(), user1, card1, user2, card2, if(hero1.healthPoints <= 0){
			card2.winner()
			if(card2.experiencePoints >= 5){
				card2.updateLevel()
			}
			user2
		}else{
			card1.winner()
			if(card1.experiencePoints >= 5){
				card2.updateLevel()
			}
			user1
		}).apply { registerCombat(this) }
	}

	private fun User.haveCard(card: Card): Boolean = deck.cards.find { it.id == card.id } != null

	private fun Card.isLowerLevelOrEqualThan(other: Card): Boolean = level <= other.level

	private fun Card.winner() {
		experiencePoints += 1
		cardRepo.update(this.id, this)
	}
	
	private fun Card.updateLevel(){
		experiencePoints = 0
		level += 1
		cardRepo.update(this.id, this)
	}
	
	private fun registerCombat(combat: Combat){
		combatRepo.create(combat)
	}
}