package serializer.enums.string

import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import serializer.enums.AbstractEnumSerializer
import serializer.enums.TEnum
import kotlin.reflect.KClass

abstract class AbstractEnumStringSerializer<C: TEnum<String>>(enumClass: KClass<C>): AbstractEnumSerializer<C, String>(enumClass, PrimitiveKind.STRING) {
	override fun decodeValue(decoder: Decoder): String = decoder.decodeString()

	override fun serialize(encoder: Encoder, value: C) {
		encoder.encodeString(value.value)
	}
}