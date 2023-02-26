package types

import serializer.enums.TEnum
import serializer.enums.string.AbstractEnumStringSerializer

object PackTypeSerializer: AbstractEnumStringSerializer<PackType>(PackType::class)

enum class PackType(override val value: String): TEnum<String> {
	SILVER("silver"),
	DIAMOND("diamond");

	override fun getValue(value: String): TEnum<String> = values().find { it.value == value }!!
}