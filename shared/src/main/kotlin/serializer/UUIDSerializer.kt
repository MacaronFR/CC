package serializer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import java.util.UUID
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = UUID::class)
object UUIDSerializer: KSerializer<UUID> {
	override val descriptor: SerialDescriptor
		get() = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

	override fun serialize(encoder: Encoder, value: UUID) {
		encoder.encodeString(value.toString())
	}

	override fun deserialize(decoder: Decoder): UUID = UUID.fromString(decoder.decodeString())
}