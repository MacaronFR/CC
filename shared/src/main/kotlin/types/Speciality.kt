package types

import serializer.enums.TEnum
import serializer.enums.string.AbstractEnumStringSerializer

object SpecialitySerializer: AbstractEnumStringSerializer<Specialty>(Specialty::class)

enum class Specialty(override val value: String): TEnum<String> {
    TANK("tank"),
    ASSASSIN("assassin"),
    MAGE("mage");

    override fun getValue(value: String): TEnum<String> = values().find { it.value == value }!!
}
