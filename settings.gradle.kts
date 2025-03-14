pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        maven {
            name = "SpongePowered Maven"
            url = uri("https://repo.spongepowered.org/repository/maven-public/")
        }
        maven {
            name = "NeoForged Maven"
            url = uri("https://maven.neoforged.net/releases")
        }
        maven {
            name = "Fabric Maven"
            url = uri("https://maven.fabricmc.net/")
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}

rootProject.name = "projector-mc"

include("common", "neoforge", "fabric")
