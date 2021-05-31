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

    //Use TestNG test framework
    testImplementation("io.cucumber:cucumber-testng:6.10.3")
}

tasks.getByName<Test>("test") {
    useTestNG() {
        useDefaultListeners = true
    } //Use TestNG test framework
}
