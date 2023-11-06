package com.github.alxbun

import org.gradle.api.DefaultTask
import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.TaskAction
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

/**
 * The task generates a java class that contains a method that returns the version of the project.
 */
open class GenerateBuildInfoTask : DefaultTask() {

    private val generatedClass = { version: String ->
        """
        package buildinfo;
        
        public class BuildInfo {

            public static String version() {
                return "$version";
            }
        }
        """
    }

    @TaskAction
    fun invoke() {
        val dir = Paths.get("${project.buildDir}/generated/sources/java/buildinfo")
        Files.createDirectories(dir)
        val file = Paths.get("${project.buildDir}/generated/sources/java/buildinfo/BuildInfo.java")
        println("Path to the generated file: $file")

        try {
            if (Files.exists(file)) Files.delete(file)

            Files.createFile(file)

            Files.write(file, listOf(generatedClass(project.version.toString())), StandardCharsets.UTF_8)
        } catch (e: Exception) {
            logging.captureStandardOutput(LogLevel.ERROR)
            println("Can't create file: $file")
        }
    }
}