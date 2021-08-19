import wtf.gofancy.fancygradle.patch.Patch
import wtf.gofancy.fancygradle.script.extensions.createDebugLoggingRunConfig
import wtf.gofancy.fancygradle.script.extensions.curse
import wtf.gofancy.fancygradle.script.extensions.curseForge
import wtf.gofancy.fancygradle.script.extensions.deobf

import java.time.format.DateTimeFormatter
import java.time.Instant

plugins {
    java
    idea
    id("net.minecraftforge.gradle") version "4.1.10"
    id("wtf.gofancy.fancygradle") version "1.0.1"
}

version = "1.5.6"
group = "mrthomas20121.tfc_tinkers"

fancyGradle {
    patches {
        patch(Patch.CODE_CHICKEN_LIB, Patch.RESOURCES, Patch.COREMODS, Patch.ASM)
    }
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(8))
idea.module.inheritOutputDirs = true

repositories {
	curseForge()
    maven {
        name = "Craftweaker"
        url = uri("https://maven.blamejared.com")
    }
    maven { 
        name = "Slimeknight maven"
        url = uri("http://dvs1.progwml6.com/files/maven")
    }
    maven {
        name = "TOP maven"
        url = uri("http://maven.tterrag.com/")
    }
    maven {
        name = "Cofh Maven"
        url = uri("http://maven.covers1624.net")
    }
}

minecraft {
    mappings("stable", "39-1.12")

    runs {
        createDebugLoggingRunConfig("client")
        createDebugLoggingRunConfig("server") { args("nogui") }
    }
}

dependencies {
    minecraft(group = "net.minecraftforge", name = "forge", version = "1.12.2-14.23.5.2855")
    
	// jei
	implementation(fg.deobf(group = "mezz.jei", name = "jei_1.12.2", version = "4.16.1.302"))
	
    // tinkers construct
    implementation(fg.deobf(group = "slimeknights.mantle", name = "Mantle", version = "1.12-1.3.3.55"))
	implementation(fg.deobf(group = "slimeknights", name = "TConstruct", version = "1.12.2-2.13.0.183"))

    // construct armory
    implementation(fg.deobf(curse(mod = "constructs-armory", projectId = 287683L, fileId = 3174535L)))

	// crafttweaker
	implementation(fg.deobf(group = "CraftTweaker2", name = "CraftTweaker2-MC1120-Main", version = "1.12-4.1.20.656"))
	implementation(fg.deobf(group = "com.blamejared", name = "MTLib", version = "3.0.4.8"))

	// tfc
	implementation(fg.deobf(curse(mod = "tfcraft", projectId = 302973L, fileId = 3406948L)))
	
    // tfc metallum
	implementation(fg.deobf(curse(mod = "tfc-metallum", projectId = 339156L, fileId = 3062588L)))
    
    // rocksalt version 1.0.4b
    implementation(fg.deobf(curse(mod = "rocksalt", projectId = 398969L, fileId = 3419071L)))
    
    // bio library
    implementation(fg.deobf(curse(mod = "biolib", projectId = 390354L, fileId = 3411757L)))
}

tasks {
    jar {
        archiveBaseName.set("tfc_tinkers")
        finalizedBy("reobfJar")
        manifest {
            attributes(
                "Specification-Title" to project.name,
                "Specification-Version" to project.version,
                "Specification-Vendor" to "Mrthomas20121",
                "Implementation-Title" to "${project.group}.${project.name}",
                "Implementation-Version" to project.version,
                "Implementation-Vendor" to "Mrthomas20121",
                "Implementation-Timestamp" to DateTimeFormatter.ISO_INSTANT.format(Instant.now())
            )
        }
    }

    processResources {
        inputs.property("version", project.version)

        filesMatching("mcmod.info") {
            expand("version" to project.version,"mcversion" to "1.12.2")
        }
    }
}
