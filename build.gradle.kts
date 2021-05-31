plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    //testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    //Use JUnit test framework
    testImplementation("io.cucumber:cucumber-junit:6.10.3")
    testImplementation("io.cucumber:cucumber-java:6.10.3")

    //Use TestNG test framework
    testImplementation("io.cucumber:cucumber-testng:6.10.3")
}

/*tasks.getByName<Test>("test") {
    useJUnitPlatform()
    //useTestNG() //Use TestNG test framework
}*/