plugins {
    kotlin("jvm") version "1.8.10" apply false
    kotlin("plugin.serialization") version "1.8.10" apply false
}

allprojects {

    group = "fr.imacaron"

    version = 1.0

    repositories {
        mavenCentral()
    }
}