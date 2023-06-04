import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `maven-publish`
    kotlin("jvm") version "1.8.21"
}

group = "site.alxbun"
version = "1.0-SNAPSHOT"

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
    plugins {
        create("buildInfoPlugin") {
            id = "$group.build-info"
            implementationClass = "$group.BuildInfoPlugin"
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