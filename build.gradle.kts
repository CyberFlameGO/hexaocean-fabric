plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.loom)
    alias(libs.plugins.kapt)
    `maven-publish`
    java
}

repositories {
    maven(url = "https://maven.fabricmc.net/") {
        name = "Fabric"
    }
    mavenCentral()
}

dependencies {
    minecraft(libs.minecraft)
    mappings("${libs.yarn.get()}:v2")
    modImplementation(libs.bundles.fabric)
    kapt(libs.fabric.mixin)
}

tasks {
    processResources {
        filesMatching("fabric.mod.json") {
            expand(mutableMapOf("version" to project.version))
        }
    }
    jar {
        from("LICENSE.txt") {
            rename { "${it}_hexaocean-fabric" }
        }
    }
    java {
        withSourcesJar()
    }
    withType<JavaCompile> {
        // Minecraft 1.18 (1.18-pre2) upwards uses Java 17.
        options.release.set(17)
    }
}
