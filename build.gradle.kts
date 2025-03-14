import org.cadixdev.gradle.licenser.Licenser

plugins {
    java
    idea
    alias(libs.plugins.licenser)
    alias(libs.plugins.neoforge.gradle) apply false
    alias(libs.plugins.fabric.loom) apply false
}

// Properties
internal val mod_id: String by rootProject.extra
internal val mod_group_id: String by rootProject.extra
internal val mod_version: String by rootProject.extra
internal val mod_license: String by rootProject.extra
internal val mod_authors: String by rootProject.extra

// Setup subprojects
subprojects {
    // Java settings
    plugins.withType<JavaPlugin> {
        base.archivesName = "$mod_id-${project.name}"
        group = mod_group_id
        version = mod_version

        java.toolchain.languageVersion.set(JavaLanguageVersion.of(libs.versions.java.get()))

        tasks.withType<JavaCompile> {
            options.encoding = "UTF-8"
        }
    }

    // IDEA settings
    plugins.withType<IdeaPlugin> {
        idea.module {
            isDownloadSources = true
            isDownloadJavadoc = true
        }
    }

    // Duplicate resources
    tasks.withType<ProcessResources>().configureEach {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    // Licenser settings
    plugins.withType<Licenser> {
        license {
            header = rootProject.resources.text.fromFile("HEADER")
            properties {
                "mod_authors" to mod_authors
                "mod_license" to mod_license
            }
            include("**/*.java")
        }
    }
}