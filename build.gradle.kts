group = "me.watermelon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

plugins {
    id("java") //A Gradle Core plugin: Provides support for building Java projects.
    kotlin("jvm")
    id("org.jetbrains.intellij.platform") version "2.1.0"
}

dependencies {
    //IntelliJ Platform Gradle Plugin enhances the dependencies {} configuration block by applying a nested dependencies.intellijPlatform {} extension.
    intellijPlatform {
        intellijIdeaCommunity("2024.2") //Target IDE Platform and Version
        bundledPlugin("com.intellij.java")
        bundledPlugin("Git4Idea")
        javaCompiler()
    }
    implementation(kotlin("stdlib-jdk8"))
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