import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.1.8.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.3.50"
	kotlin("plugin.spring") version "1.3.50"
	kotlin("plugin.jpa") version "1.3.50"
	jacoco
}

group = "com.example.kotlionspringboot"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

jacoco {
	toolVersion = "0.8.4"
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("net.logstash.logback:logstash-logback-encoder:6.2")

	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.kafka:spring-kafka:2.2.8.RELEASE")

	// database
	runtimeOnly("com.h2database:h2")
	implementation("org.postgresql:postgresql:42.2.6")
	implementation("org.liquibase:liquibase-core:3.8.0")

	testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
	testCompile("org.junit.jupiter:junit-jupiter-params:5.2.0")
	testRuntime("org.junit.jupiter:junit-jupiter-engine:5.2.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(module="junit")
	}
	testImplementation("io.mockk:mockk:1.9.3")

	testImplementation("org.springframework.kafka:spring-kafka-test")

	runtimeOnly("io.micrometer:micrometer-registry-prometheus")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.named<Test>("test") {
	useJUnitPlatform()

	finalizedBy("jacocoTestReport")
}

tasks.jacocoTestReport {
	reports {
		csv.isEnabled = false
		html.isEnabled = false
		xml.isEnabled = true
		xml.destination = file("$buildDir/reports/coverage/jacoco.xml")
	}
}
