import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.30"
    `java-library`
    idea
    id("me.champeau.jmh") version "0.6.6"
}

idea {
    module {
        excludeDirs.add(project.rootDir.resolve("node_modules"))
    }
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

java {
    sourceCompatibility = JavaVersion.VERSION_16
    targetCompatibility = JavaVersion.VERSION_16
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_16.majorVersion
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }
}

jmh {
    failOnError.set(true)
    resultFormat.set("JSON")
    val reportsDirectory = File("build/reports/jmh/").apply { mkdirs() }
    resultsFile.set(reportsDirectory.resolve("report.json"))
    val pattern = project.findProperty("benchmark")?.toString() ?: ".*Benchmark"
    includes.add(pattern)
}
