plugins {
	kotlin("jvm")
	application
	kotlin("plugin.serialization")
}

val ktor_version: String by project

dependencies {
	implementation(project(":shared"))
	implementation(project(":domain"))
	implementation(project(":repositories"))
	implementation("io.ktor:ktor-server-core:$ktor_version")
	implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
	implementation("io.ktor:ktor-server-resources:$ktor_version")
	implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
	implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
	implementation("org.ktorm:ktorm-core:3.6.0")
	implementation("ch.qos.logback:logback-classic:1.4.5")
	implementation("org.mariadb.jdbc:mariadb-java-client:3.0.6")


	testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

tasks.getByName<Test>("test") {
	useJUnitPlatform()
}