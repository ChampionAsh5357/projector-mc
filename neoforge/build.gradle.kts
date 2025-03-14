plugins {
    `java-library`
    idea
    alias(libs.plugins.neoforge.gradle)
}

enum class VersionComponent(val index: Int) {
    MAJOR(0), MINOR(1), PATCH(2)
}

internal fun computeNextVersion(version: String, component: VersionComponent = VersionComponent.PATCH): String {
    val components: MutableList<String> = version.split('.').toMutableList()
    components[component.index] = (components[component.index].toInt() + 1).toString()
    return components.joinToString(".")
}

internal val mod_id: String by rootProject.extra
internal val mod_name: String by rootProject.extra
internal val mod_license: String by rootProject.extra
internal val mod_version: String by rootProject.extra
internal val mod_authors: String by rootProject.extra
internal val mod_description: String by rootProject.extra
internal val neoforge_loader_version_range: String by rootProject.extra

internal val minecraft_version: String = libs.versions.minecraft.get()
internal val neoforge_version: String = libs.versions.neoforge.version.get()

internal val generated: SourceSet = sourceSets.create("generated") {
    java.setSrcDirs(emptyList<Any>())
}

val generateModMetadata: TaskProvider<ProcessResources> = tasks.register<ProcessResources>("generateModMetadata") {
    val replaceProperties: Map<String, String> = mapOf(
        "minecraft_version" to minecraft_version,
        "minecraft_version_range" to "[$minecraft_version, ${computeNextVersion(minecraft_version)})",
        "neo_version" to neoforge_version,
        "neo_version_range" to "[$neoforge_version,)",
        "loader_version_range" to neoforge_loader_version_range,
        "mod_id" to mod_id,
        "mod_name" to mod_name,
        "mod_license" to mod_license,
        "mod_version" to mod_version,
        "mod_authors" to mod_authors,
        "mod_description" to mod_description
    )

    inputs.properties(replaceProperties)
    expand(replaceProperties)
    from(project.relativePath("src/main/templates"))
    into(project.relativePath("build/generated/sources/modMetadata"))
}

sourceSets["main"].resources {
    source(generated.resources)
    srcDir(generateModMetadata)
    exclude("./cache")
}

val common: Project = rootProject.project(":common")

project.evaluationDependsOn(common.path)
tasks.withType<JavaCompile> {
    source(common.sourceSets["main"].allSource)
}
tasks.withType<ProcessResources> {
    from(common.sourceSets["main"].resources)
}
dependencies {
    implementation(common)
}

neoForge {
    version = neoforge_version
    parchment {
        mappingsVersion = libs.versions.parchment.get()
        minecraftVersion = minecraft_version
    }
    ideSyncTask(generateModMetadata)

    runs {
        create("client") {
            client()
            systemProperty("neoforge.enabledGameTestNamespaces", mod_id)
        }
        create("server") {
            server()
            programArgument("--nogui")
            systemProperty("neoforge.enabledGameTestNamespaces", mod_id)
        }
        create("gameTestServer") {
            type = "gameTestServer"
            systemProperty("neoforge.enabledGameTestNamespaces", mod_id)
        }
        create("clientData") {
            clientData()
            programArguments.addAll("--mod", mod_id, "--all", "--output", generated.resources.srcDirs.first().absolutePath)
            sourceSets["main"].resources.srcDirs.forEach { programArguments.addAll("--existing", it.absolutePath) }
        }

        configureEach {
            systemProperty("forge.logging.markers", "REGISTRIES")
            logLevel = org.slf4j.event.Level.DEBUG
        }
    }

    mods.create(mod_id) {
        sourceSet(sourceSets["main"])
        sourceSet(common.sourceSets["main"])
    }
}
