plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("io.cucumber:cucumber-java:6.10.3")
    testImplementation("io.cucumber:cucumber-junit:6.10.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    //Use TestNG test framework
    testImplementation("io.cucumber:cucumber-testng:6.10.3")
}


