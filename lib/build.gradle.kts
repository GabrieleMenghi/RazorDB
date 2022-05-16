// Works with Gradle 6.3 and Java 14

// To run this do:
// ./gradlew run

// To build a distribution zip:
// ./gradlew distZip

plugins {
    id 'java'
    id 'application'
    id 'org.openjfx.javafxplugin' version '0.0.8'
}

mainClassName = "razorDB.App"

javafx {
    version = "14"
    modules = [ 'javafx.controls', 'javafx.fxml']
}

repositories {
    mavenCentral()
}
