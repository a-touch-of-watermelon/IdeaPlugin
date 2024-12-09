pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        kotlin("jvm") version "2.0.21"
    }
}

plugins {
    //If you use Gradle 8.0.2 or higher, you also need to add a toolchain resolver plugin.
    //  This type of plugin manages which repositories to download a toolchain from
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "IdeaPlugin"