import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `maven-publish`
    id("com.gradle.plugin-publish") version "1.2.0"
    kotlin("jvm") version "1.8.21"
}

group = "site.alxbun"
description = "Gradle Buildinfo Plugin"
version = "1.0.0"

java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

gradlePlugin {
    website.set("https://github.com/alxbun/BuildInfo-Plugin")
    vcsUrl.set("https://github.com/alxbun/BuildInfo-Plugin.git")
    plugins {
        create("buildInfoPlugin") {
            id = "$group.build-info"
            implementationClass = "$group.BuildInfoPlugin"
            displayName = project.name
            description = project.description
            tags.set(listOf("BuildInfo", "Version"))
        }
    }
}

publishing {
    publications {
        create<MavenPublication>(project.name) {
            from(components["kotlin"])
        }
        repositories {
            mavenLocal()
        }
    }
}