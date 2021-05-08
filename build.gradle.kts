import org.spongepowered.gradle.plugin.config.PluginLoaders
import org.spongepowered.plugin.metadata.PluginDependency

plugins {
    `java-library`
    id("org.spongepowered.gradle.plugin") version "1.0.3"
}

group = "org.spongepowered"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

sponge {
    apiVersion("8.0.0")
    plugin("skeletic-mobs-rewritten") {
        loader(PluginLoaders.JAVA_PLAIN)
        displayName("Skeletic Mobs Rewritten")
        mainClass("dev.techiepi.skeleticmobsrewritten.SkeleticMobs")
        description("A complete (and decent) rewrite of the original Skeletic Mobs")
        links {
            homepage("https://github.com/Techie-Pi/SkeleticMobsRewritten#readme")
            source("https://github.com/Techie-Pi/SkeleticMobsRewritten")
            issues("https://github.com/Techie-Pi/SkeleticMobsRewritten/issues")
        }
        contributor("TechiePi") {
            description("Lead Developer")
        }
        dependency("spongeapi") {
            loadOrder(PluginDependency.LoadOrder.AFTER)
            optional(false)
        }
    }
}

val javaTarget = 8 // Sponge targets a minimum of Java 8
java {
    sourceCompatibility = JavaVersion.toVersion(javaTarget)
    targetCompatibility = JavaVersion.toVersion(javaTarget)
}

tasks.withType(JavaCompile::class).configureEach {
    options.apply {
        encoding = "utf-8" // Consistent source file encoding
        if (JavaVersion.current().isJava10Compatible) {
            release.set(javaTarget)
        }
    }
}

// Make sure all tasks which produce archives (jar, sources jar, javadoc jar, etc) produce more consistent output
tasks.withType(AbstractArchiveTask::class).configureEach {
    isReproducibleFileOrder = true
    isPreserveFileTimestamps = false
}