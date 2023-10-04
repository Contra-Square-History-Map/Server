import com.google.protobuf.gradle.id
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.4"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.9.10"
	kotlin("plugin.spring") version "1.9.10"
	kotlin("plugin.jpa") version "1.9.10"

    id("com.google.protobuf") version "0.9.4"
}

group = "afrock.dance"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("com.google.protobuf:protobuf-kotlin:3.24.3")
	implementation("com.google.protobuf:protobuf-java-util:3.24.3")

	runtimeOnly("org.postgresql:postgresql")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

sourceSets {
	main {
		java {
			srcDirs("src/main/java")
			srcDirs("build/generated/source/proto/main/java")
		}
		kotlin {
			srcDirs("src/main/kotlin")
			srcDirs("build/generated/source/proto/main/kotlin")
		}
		proto {
			srcDir("src/main/proto")
		}
	}
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.24.3"
    }

    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
				id("kotlin")
            }
        }
    }
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
