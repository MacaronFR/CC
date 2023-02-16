plugins {
    kotlin("jvm") version "1.8.0"
}

group = "fr.imacaron"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}