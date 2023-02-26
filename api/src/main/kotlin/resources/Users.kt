package resources

import io.ktor.resources.*
import kotlinx.serialization.Serializable
import serializer.UUIDSerializer
import types.PackType
import types.PackTypeSerializer
import java.util.UUID

@Resource("/users")
class Users(val search: String? = null) {

	@Resource("{id}")
	class Id(val parent: Users, @Serializable(with = UUIDSerializer::class) val id: UUID){
		@Resource("open")
		class Open(val parent: Id){
			@Resource("{type}")
			class Type(val parent: Open, val type: @Serializable(with = PackTypeSerializer::class) PackType)
		}
	}
}