import org.sourcegrade.submitter.submit

plugins {
    java
    application
    id("org.sourcegrade.submitter").version("0.4.0")
}

submit {
    assignmentId = "h12" // do not change assignmentId
    studentId = "pb38jyxa"
    firstName = "Patrick"
    lastName = "Blauth"
    // Optionally require tests for prepareSubmission task. Default is true
    requireTests = true
}

// !! Achtung !!
// Die studentId (TU-ID) ist keine Matrikelnummer
// Richtig z.B. ab12cdef
// Falsch z.B. 1234567

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
    // JUnit only available in "test" source set (./src/test)
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

application {
    mainClass.set("h12.Main")
}

tasks {
    val runDir = File("build/run")
    named<JavaExec>("run") {
        doFirst {
            runDir.mkdirs()
        }
        workingDir = runDir
    }
    test {
        doFirst {
            runDir.mkdirs()
        }
        workingDir = runDir
        useJUnitPlatform()
    }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }
}
