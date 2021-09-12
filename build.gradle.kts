import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.30"
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    // main
    implementation(platform(kotlin("bom")))
    implementation(kotlin("stdlib-jdk8"))

    // tests
    val kotestVersion: String by project

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "16"
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }
}
