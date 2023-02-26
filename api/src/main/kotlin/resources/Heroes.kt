package resources

import io.ktor.resources.*
import kotlinx.serialization.Serializable
import serializer.UUIDSerializer
import java.util.UUID

@Resource("/heroes")
class Heroes{
	@Resource("{id}")
	class Id(val parent: Heroes, @Serializable(with = UUIDSerializer::class) val id: UUID)
}