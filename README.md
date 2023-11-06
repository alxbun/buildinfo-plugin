# BuildInfo-Plugin

This Gradle plugin is analogue of sbt-buildinfo plugin for SBT, which generates a Java class with a version of the Gradle project.

Generated file will be located in ${project.buildDir}/generated/sources/java/buildinfo/BuildInfo.java

## Usage: 
```
plugins {
  id("com.github.alxbun.build-info") version "1.0.2"
}
```
