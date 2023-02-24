package resources

import io.ktor.resources.*
import kotlinx.serialization.Serializable
import serializer.UUIDSerializer
import java.util.UUID

@Resource("/users")
class Users {
	@Resource("{id}")
	class Id(val parent: Users, @Serializable(with = UUIDSerializer::class) val id: UUID)
}