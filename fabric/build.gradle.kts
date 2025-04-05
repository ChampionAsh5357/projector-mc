import groovy.json.JsonOutput
import net.fabricmc.loom.configuration.ide.idea.IdeaSyncTask
import java.io.FileWriter
import java.nio.file.Files
import java.util.*
import kotlin.collections.List

plugins {
    alias(libs.plugins.fabric.loom)
}

internal val mod_id: String by rootProject.extra
internal val mod_name: String by rootProject.extra
internal val mod_license: String by rootProject.extra
internal val mod_version: String by rootProject.extra
internal val mod_authors: String by rootProject.extra
internal val mod_description: String by rootProject.extra
internal val mod_group_id: String by rootProject.extra

internal val java_version: String = libs.versions.java.get()
internal val minecraft_version: String = libs.versions.minecraft.get()

internal val modMainClass: String = "${project.name.replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
}}${mod_id.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }}"

internal val generated: SourceSet = sourceSets.create("generated") {
    java.setSrcDirs(emptyList<Any>())
}

val generateModMetadata: TaskProvider<ProcessResources> = tasks.register<ProcessResources>("generateModMetadata") {
    val replaceProperties: Map<String, String> = mapOf(
        "mod_id" to mod_id,
        "mod_version" to mod_version,
        "mod_name" to mod_name,
        "mod_description" to mod_description,
        "mod_authors" to mod_authors,
        "mod_license" to mod_license,
        "mod_entrypoint" to "$mod_group_id.$mod_id.${project.name}.${modMainClass}",
        "mod_client_entrypoint" to "$mod_group_id.$mod_id.${project.name}.client.${modMainClass}Client",
        "mod_data_entrypoint" to "$mod_group_id.$mod_id.${project.name}.data.${modMainClass}Data",
        "minecraft_version" to minecraft_version,
        "fabric_loader_version" to libs.versions.fabric.loader.get(),
        "java_version" to java_version,
        "fabric_api_version" to libs.versions.fabric.api.get()
    )

    inputs.properties(replaceProperties)
    expand(replaceProperties)
    from(project.relativePath("src/main/templates"))
    into(project.relativePath("build/generated/sources/modMetadata"))
}

val generateClientMixins: TaskProvider<Task> = tasks.register("generateClientMixins") {
    val mixinPath: String = listOf(mod_group_id.replace(".", File.separator), mod_id, project.name, "api", "client", "mixin").joinToString(File.separator)
    val mixins: List<String> = sourceSets["main"].allSource.asSequence().filter {
        it.path.contains(mixinPath)
    }.map {
        it.path.split("$mixinPath${File.separator}").last().substringBeforeLast('.').replace(File.separator, ".")
    }.toList()

    val json: String = JsonOutput.prettyPrint(JsonOutput.toJson(mapOf(
        "required" to true,
        "package" to "$mod_group_id.$mod_id.${project.name}.api.client.mixin",
        "compatibilityLevel" to "JAVA_$java_version",
        "client" to mixins,
        "injectors" to mapOf(
            "defaultRequire" to 1
        )
    )))

    val filePath: File = project.file("build/generated/sources/clientMixins/$mod_id.client.mixins.json")
    Files.createDirectories(filePath.parentFile.toPath())
    FileWriter(filePath).use {
        it.write(json)
    }
    outputs.dir(filePath.parentFile)
}

val common: Project = rootProject.project(":common")
project.evaluationDependsOn(common.path)
tasks.withType<JavaCompile> {
    source(common.sourceSets["main"].allSource)
}

sourceSets["main"].resources {
    source(generated.resources)
    srcDir(generateModMetadata)
    srcDir(generateClientMixins)
    srcDirs(common.sourceSets["main"].resources)
    exclude("./cache")
}
tasks.withType<IdeaSyncTask>().forEach {
    it.finalizedBy(generateModMetadata)
    it.finalizedBy(generateClientMixins)
}

repositories {
    maven {
        name = "ParchmentMC"
        url = uri("https://maven.parchmentmc.org")
    }
}

dependencies {
    minecraft(libs.minecraft)
    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${libs.versions.parchment.minecraft.get()}:${libs.versions.parchment.version.get()}@zip")
    })
    modImplementation(libs.bundles.fabric)
    implementation(common)
}

loom {
    runs {
        named("client") {
            client()
            configName = "Fabric Client"
            ideConfigGenerated(true)
        }
        named("server") {
            server()
            configName = "Fabric Server"
            ideConfigGenerated(true)
        }
    }
}

fabricApi.configureDataGeneration {
    client = true
    createRunConfiguration = true
    outputDirectory = generated.resources.srcDirs.first()
}
