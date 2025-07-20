plugins {
    java
    application
    id("org.jetbrains.kotlin.jvm") version "1.9.23"
    id("org.javamodularity.moduleplugin") version "1.8.12"
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("org.beryx.jlink") version "2.25.0"
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "org.girsang"
version = "1.0-SNAPSHOT"

application {
    mainModule.set("org.girsang.pos")
    mainClass.set("org.girsang.pos.HelloApplicationKt")
}
java {
    modularity.inferModulePath.set(true)
}

repositories {
    mavenCentral()
}

val junitVersion = "5.10.2"


tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

application {
    mainModule.set("org.girsang.pos")
    mainClass.set("org.girsang.pos.HelloApplication")
}
kotlin {
    jvmToolchain(21)
}

javafx {
    version = "17.0.6"
    modules = listOf("javafx.controls", "javafx.fxml")
}

dependencies {
    implementation(kotlin("stdlib"))
    val javafxVersion = "17.0.6"
    val osName = when {
        org.gradle.internal.os.OperatingSystem.current().isWindows -> "win"
        org.gradle.internal.os.OperatingSystem.current().isMacOsX -> "mac"
        else -> "linux"
    }

    listOf("base", "graphics", "controls", "fxml").forEach {
        implementation("org.openjfx:javafx-$it:$javafxVersion:$osName")
    }
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.h2database:h2:2.3.232")

    testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

jlink {
    imageZip.set(layout.buildDirectory.file("/distributions/app-${javafx.platform.classifier}.zip"))
    options.set(listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages"))
    launcher {
        name = "app"
    }
}
