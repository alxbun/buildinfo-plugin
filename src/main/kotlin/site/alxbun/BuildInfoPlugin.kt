package site.alxbun

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType

/**
 * BuildInfo plugin registers a task that creates a generated file with the project version.
 *
 * @see GenerateBuildInfoTask
 */
class BuildInfoPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val buildInfoTask = target.tasks.register<GenerateBuildInfoTask>("generateBuildInfoClass")

        target.tasks.withType<JavaCompile>().configureEach {
            dependsOn(buildInfoTask)
        }

        target.afterEvaluate {
            (target.extensions["sourceSets"] as SourceSetContainer)["main"]
                .java
                .srcDir("${project.buildDir}/generated/sources/java/")
        }
    }
}