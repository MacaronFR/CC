import adapters.cards.KtormCardsRepository
import adapters.combats.KtormCombatRepository
import adapters.decks.KtormDecksRepository
import adapters.heroes.KtormHeroesRepository
import adapters.users.KtormUsersRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import org.ktorm.database.Database
import plugins.contentNegotiation
import plugins.resources
import ports.NotFoundException
import ports.out.exception.NoTokenLeftException
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
	val db = Database.connect("jdbc:mariadb://localhost:3306/CC", user = "root", password = "root")
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
	install(StatusPages) {
		exception<Throwable> { call, cause ->
			when(cause){
				is NotFoundException -> call.respondText("404: ${cause.message}", status = HttpStatusCode.NotFound)
				is NoTokenLeftException -> call.respondText("400: No more token left", status = HttpStatusCode.BadRequest)
				else -> {
					cause.printStackTrace()
					call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
				}
			}
		}
	}
	installUsers(userCreator, userSearch, userUpdater, packOpener, userRemoveToken, userAddToken, cardGetter, startCombat)
	installCombats(combatSearcher)
}