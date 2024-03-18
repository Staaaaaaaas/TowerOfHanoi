import io.gitlab.arturbosch.detekt.Detekt

val jdkVersion = libs.versions.jdk.get()

plugins {
    kotlin("jvm") version "1.9.22"
    alias(libs.plugins.detekt)
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.std)
    detektPlugins(libs.detekt.formatting)
    testImplementation(libs.kotlin.test)
}

tasks {
    withType<Detekt>().configureEach {
        jvmTarget = jdkVersion
    }

    jar {
        manifest.attributes["Main-Class"] = "org.example.MainKt"
        val dependencies = configurations
            .runtimeClasspath
            .get()
            .map(::zipTree)
        from(dependencies)
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    test {
        useJUnitPlatform()
    }
}
kotlin {
    jvmToolchain(19)
}