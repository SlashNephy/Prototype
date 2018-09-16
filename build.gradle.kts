import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.2.70"
    application
}

application {
    mainClassName = "jp.nephy.prototype.PrototypeApp"
}

group = "jp.nephy"

repositories {
    mavenCentral()
    jcenter()
    maven(url = "https://kotlin.bintray.com/ktor")
}

dependencies {
    compile(kotlin("stdlib-jdk8"))

    compile("no.tornado:tornadofx:1.7.17")

    compile("jp.nephy:penicillin:3.0.10")
    compile("org.jetbrains.kotlinx:atomicfu:0.11.3")

    compile("io.github.microutils:kotlin-logging:1.5.9")
    compile("ch.qos.logback:logback-core:1.2.3")
    compile("ch.qos.logback:logback-classic:1.2.3")
    compile("org.fusesource.jansi:jansi:1.17.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

kotlin {
    experimental.coroutines = Coroutines.ENABLE
}

val fatJar = task("fatJar", type = Jar::class) {
    baseName = "${project.name}-full"
    manifest {
        attributes["Main-Class"] = "jp.nephy.prototype.PrototypeApp"
    }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    from(configurations.runtime.map { if (it.isDirectory) it else zipTree(it) })
    with(tasks["jar"] as CopySpec)
}

tasks {
    "build" {
        dependsOn(fatJar)
    }
}
