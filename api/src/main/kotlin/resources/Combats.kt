package resources

import io.ktor.resources.*
import kotlinx.serialization.Serializable
import serializer.UUIDSerializer
import java.util.UUID

@Resource("/combats")
class Combats {
	@Resource("user")
	class User(val parent: Combats) {

		@Resource("{id}")
		class Id(val parent: User, @Serializable(with = UUIDSerializer::class) val id: UUID)
	}

	@Resource("hero")
	class Hero(val parent: Combats){

		@Resource("{id}")
		class Id(val parent: Hero, @Serializable(with = UUIDSerializer::class) val id: UUID)
	}
}