package resources

import io.ktor.resources.*
import kotlinx.serialization.Serializable
import serializer.UUIDSerializer
import java.util.UUID

@Resource("/decks")
class Decks{
	@Resource("{id}")
	class Id(val parent: Decks = Decks(), @Serializable(with = UUIDSerializer::class)val id: UUID){
		@Resource("append")
		class Append(val parent: Id)
	}
}