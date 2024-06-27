val exclusions = listOf(
    "**/R.class",
    "**/R\$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/*Test*.*",
    "**/di/*.*",
    "**/ui/**/*.*",
)

tasks.register<JacocoReport>("codeCoverage") {
    description = "Generates code coverage report."
    group = "verification"

    dependsOn("testDebugUnitTest")

    reports {
        html.required.set(true)
        xml.required.set(true)
    }

    classDirectories.setFrom(files(
        fileTree(layout.buildDirectory.dir("intermediates/javac/")) {
            exclude(exclusions)
        },
        fileTree(layout.buildDirectory.dir("tmp/kotlin-classes/")) {
            exclude(exclusions)
        }
    ))

    sourceDirectories.setFrom(layout.projectDirectory.dir("src/main"))

    executionData.setFrom(files(
        fileTree(layout.buildDirectory) { include(listOf("**/*.exec", "**/*.ec")) }
    ))
}