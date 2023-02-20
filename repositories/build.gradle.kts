plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

group = "fr.imacaron"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.ktorm:ktorm-core:3.6.0")
    
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation(project(":shared"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}