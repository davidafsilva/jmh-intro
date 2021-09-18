## Gradle Integration
JMH integration can be easily integrated with Gradle by relying on the
popular [jmh-gradle-plugin](https://github.com/melix/jmh-gradle-plugin). It provides everything with reasonable defaults
to speed things up:
- Source set configured for `src/jmh`, similarly to `main` and `test`
- Built-in set of tasks to handle the compilation and tests execution
  - The most relevant being `jmh` which executes the benchmarks

### Apply the plugin
```kotlin
plugins {
    id("me.champeau.jmh") version "0.6.6" // check latest version on GitHub
}
```

### Relevant Configuration Options
```kotlin
// set to fail the build on error
failOnError.set(true)

// create reports in JSON (file) and textual format to the console
resultFormat.set("JSON")
val reportsDirectory = File("build/reports/jmh/").apply { mkdirs() }
resultsFile.set(reportsDirectory.resolve("report.json"))

// configure the benchmarks to run
// by default run all of them - assumes a Benchmark suffix pattern
val pattern = project.findProperty("benchmark")?.toString() ?: ".*Benchmark"
includes.add(pattern)
```
