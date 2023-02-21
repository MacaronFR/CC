package serializer.enums

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.jvm.isAccessible

abstract class AbstractEnumSerializer<C: TEnum<T>, T>(private val enumClass: KClass<C>, type: PrimitiveKind): KSerializer<C> {
	final override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("${enumClass.simpleName}Serializer", type)

	@Suppress("UNCHECKED_CAST")
	private val valuesMethod = enumClass.declaredFunctions.find { it.name == "values" }!!.also { it.isAccessible = true } as KFunction<Array<C>>

	private fun useValues(): Array<C> = valuesMethod.call()

	final override fun deserialize(decoder: Decoder): C {
		val value = decodeValue(decoder)
		return useValues().firstOrNull { it.value == value } ?: throw SerializationException("Inknown ${enumClass.simpleName} $value")
	}

	abstract fun decodeValue(decoder: Decoder): T
}