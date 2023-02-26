import adapters.cards.KtormCardsRepository
import adapters.decks.KtormDecksRepository
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
import routes.installDecks
import routes.installUsers
import services.decks.DeckAppenderService
import services.decks.DeckGetterService
import services.users.UserCreatorService
import services.users.UserSearchService
import services.users.UserUpdaterService

fun main(args: Array<String>) {
	println("Starting …")
	EngineMain.main(args)
	println("Stopping")
}

fun Application.module() {
	val db = Database.connect("jdbc:mariadb://localhost:3306/CC", user = "root", password = "root")
	val cardRepo = KtormCardsRepository(db)
	val deckRepo = KtormDecksRepository(db, cardRepo)
	val userRepo = KtormUsersRepository(db, deckRepo, cardRepo)
	val deckAppender = DeckAppenderService(deckRepo)
	val deckGetter = DeckGetterService(deckRepo)
	val userCreator = UserCreatorService(userRepo)
	val userSearch = UserSearchService(userRepo)
	val userUpdater = UserUpdaterService(userRepo)
	contentNegotiation()
	resources()
	install(StatusPages) {
		exception<Throwable> { call, cause ->
			when(cause){
				is NotFoundException -> call.respondText("404", status = HttpStatusCode.NotFound)
				else -> {
					cause.printStackTrace()
					call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
				}
			}
		}
	}
	installDecks(deckAppender, deckGetter)
	installUsers(userCreator, userSearch, userUpdater)
}