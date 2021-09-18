## Concepts

### JVM Forks
- Allows to create forks of the JVM that shall run the benchmarks
- Enables configuration of the target JVM(s) as well as its arguments
- `@Fork(2)`

### Timeout
- Sets the duration for each benchmark run
- `@Timeout(time = 10, timeUnit = SECONDS)`

### Iterations
- Running block of an actual benchmark test
- Where the benchmarked code will reside
- Configurable:
  - Amount: number of iterations
  - Duration: duration of the iteration, must be lower than the timeout

#### Warmup iterations
- Serves as an initial warm up for the code being targeted
- Ideally this would run long enough to trigger the JIT optimizations (inlining, code removal, etc)
- `@Warmup(iterations = 2, time = 5, timeUnit = SECONDS)`

#### Measure iterations
- Runs after all warmup iterations
- Where data points will be collected to aggregate and compute the measurement
- `@Measurement(iterations = 6, time = 5, timeUnit = SECONDS)`

### Benchmark Mode
- Defines what JMH shall measure on the measurement iterations
- Several modes are supported:
  - Throughput: number of ops per time unit (see below)
    - `@BenchmarkMode(Throughput)`
  - Average Time: average time it takes to run a single execution of the benchmarked method
    - `@BenchmarkMode(AverageTime)`
  - Sample Time: how long it takes to run a single execution of the benchmarked method (includes different buckets such
    as min and max)
    - `@BenchmarkMode(SampleTime)`
  - Single Shot Time: how long it takes to run a single execution of the benchmarked method without warm up (cold start)
    - `@BenchmarkMode(SingleShotTime)`
  - All: all of the above
  - `@BenchmarkMode(All)`

### Benchmark Units
- Specified the time units that will be used for the chosen benchmark mode
- `@OutputTimeUnit(MICROSECONDS)`

### Benchmark Parallelism
- Benchmarks can be annotation to run in parallel by N threads
- Using `Threads.MAX` will dynamically set the number of threads to the number of available CPUs at runtime (
  via `runtime.availableProcessors()`)
- `@Threads(4)`

### Benchmark State
- Runs code (e.g. initialization) outside of the iteration loop of the benchmarks
- These variables which hold state are called stated variables
- Typically modeled as inner static classes which hold the state variables
  - Public static class with No-arg constructor
  - Benchmark classes can be annotated themselves
- State variables are also scoped:
  - Benchmark: shared across all threads (e.g. singleton)
    - `@State(Scope.Benchmark)`
  - Group: shared across all threads of the same group, each thread group will have its own instance
    - `@State(Scope.Group)`
  - Thread: new instance for each thread, essentially all instances are distinct
    - `@State(Scope.Thread)`

### Benchmark Setup and Teardown
- Similar philosophy as used by many testing frameworks
- Annotations that mark functions to run at a specific time (called levels) during the benchmark execution
- Supported levels:
  - Trial: before/after each **run** of the benchmark
    - `@Setup(Level.Trial)`
    - `@Teardown(Level.Trial)`
  - Iteration: before/after each **iteration** of the benchmark
    - `@Setup(Level.Iteration)`
    - `@Teardown(Level.Iteration)`
  - Invocation: before/after each **method** of the benchmark
    - `@Setup(Level.Invocation)`
    - `@Teardown(Level.Invocation)`

### Benchmark function
- Tags a function with `@Benchmark` so it can be targeted by the benchmark suite
- Multiple functions can be tagged with this meta annotation
