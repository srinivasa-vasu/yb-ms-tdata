plugins {
    id("org.springframework.boot") version "2.5.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("java")
}

group = "io.humourmind"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.flywaydb:flyway-core")
    implementation("org.springdoc:springdoc-openapi-ui:1.5.9")
    implementation("co.ipdata.client:ipdata-java-client:0.2.0") {
        exclude(group = "org.slf4j")
    }
    implementation("com.yugabyte:spring-data-yugabytedb-ysql:2.3.0") {
        exclude(module = "jdbc-yugabytedb")
    }

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    compileOnly("org.projectlombok:lombok")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    implementation("com.yugabyte:jdbc-yugabytedb:42.3.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.yugabyte:testcontainers-yugabytedb:1.0.0-beta-3")
    testImplementation("org.testcontainers:junit-jupiter:1.15.3")
}


tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.bootBuildImage {
    imageName = "humourmind/${project.name}:${project.version}"
    pullPolicy = org.springframework.boot.buildpack.platform.build.PullPolicy.IF_NOT_PRESENT
}
