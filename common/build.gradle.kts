plugins {
    java
    idea
    alias(libs.plugins.vanilla.gradle)
}

minecraft {
    version(libs.versions.minecraft.get())

    accessWideners("src/main/platform/common.accesswidener")
}
