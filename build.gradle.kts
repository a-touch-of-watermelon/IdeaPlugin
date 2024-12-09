group = "me.watermelon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

plugins {
    // A Gradle Core plugin: Provides support for building Java projects. Core plugins must be specified without a version number。
    // The plugin adds many dependency configurations, such as 'implementation', 'testImplementation' and so on for dependencies,
    //   https://docs.gradle.org/current/userguide/java_plugin.html#sec:java_plugin_and_dependency_management。
    id("java")
    kotlin("jvm") //Kotlin DSL的扩展写法
    id("org.jetbrains.intellij.platform") version "2.1.0"
}

//https://docs.gradle.org/current/userguide/dependency_configurations.html
dependencies {
    //IntelliJ Platform Gradle Plugin enhances the dependencies {} configuration block by applying a nested dependencies.intellijPlatform {} extension.
    intellijPlatform {
        intellijIdeaCommunity("2024.2") //Target IDE Platform and Version
        bundledPlugin("com.intellij.java")
        bundledPlugin("Git4Idea")
        javaCompiler()
    }
    implementation(kotlin("stdlib")) //implementation: Dependencies required for both compilation and runtime.
}

//The IntelliJ Platform Gradle Plugin introduces a top-level intellijPlatform extension
intellijPlatform {
    pluginConfiguration {
        ideaVersion {
            sinceBuild.set("242")
            untilBuild.set("243.*")//年份后两位 + 大版本号 + 小数点 + 小版本号
        }
    }

    signing {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishing {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}

kotlin {
    jvmToolchain(17)
}

//register a single task called "hello"
tasks.register("hello") {
    group = "custom"
    description = "A lovely greeting task."
    doLast {
        println("Hello world!")
    }
}