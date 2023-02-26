import adapters.cards.KtormCardsRepository
import adapters.combats.KtormCombatRepository
import adapters.decks.KtormDecksRepository
import adapters.heroes.KtormHeroesRepository
import adapters.users.KtormUsersRepository
import io.ktor.server.application.*
import io.ktor.server.netty.*
import org.ktorm.database.Database
import plugins.callLogging
import plugins.contentNegotiation
import plugins.resources
import plugins.statusPage
import routes.installCombats
import routes.installUsers
import services.cards.CardGetterService
import services.combats.CombatSearcherService
import services.combats.StartCombatService
import services.packs.PackOpenerService
import services.users.*

fun main(args: Array<String>) {
	println("Starting …")
	EngineMain.main(args)
	println("Stopping")
}

fun Application.module() {
	val db = Database.connect(
			environment.config.property("database.url").getString(),
			user = environment.config.property("database.user").getString(),
			password = environment.config.property("database.password").getString()
	)
	val cardRepo = KtormCardsRepository(db)
	val combatRepo = KtormCombatRepository(db, cardRepo)
	val deckRepo = KtormDecksRepository(db, cardRepo)
	val heroRepo = KtormHeroesRepository(db)
	val userRepo = KtormUsersRepository(db, deckRepo, cardRepo)
	val userCreator = UserCreatorService(userRepo)
	val userSearch = UserSearchService(userRepo)
	val userUpdater = UserUpdaterService(userRepo)
	val userRemoveToken = UserRemoveTokenService(userRepo)
	val userAddToken = UserAddTokenService(userRepo, combatRepo)
	val packOpener = PackOpenerService(cardRepo, heroRepo)
	val cardGetter = CardGetterService(cardRepo)
	val startCombat = StartCombatService(combatRepo, cardRepo, userRepo)
	val combatSearcher = CombatSearcherService(combatRepo)
	contentNegotiation()
	resources()
	statusPage()
	callLogging()
	installUsers(userCreator, userSearch, userUpdater, packOpener, userRemoveToken, userAddToken, cardGetter, startCombat)
	installCombats(combatSearcher)
}