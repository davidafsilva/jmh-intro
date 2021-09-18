# JMH (Java Microbenchmark Harness)

## Overview
- Framework for building any type of benchmarks
  - Usually nano/micro
- Targets all\* languages within the JVM ecosystem
- Meta annotation driven
  - Generates synthetic benchmark code from our own code + annotations
- Runs as standalone application
  - Can be plugged in via build too plugins, such as Gradle
  - There are plugins for IDEs as well
- Benchmark classes must be open (relevant for Kotlin)
- Repository: https://github.com/openjdk/jmh
