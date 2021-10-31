package pt.davidafsilva.jvm.jmh

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Mode.Throughput
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.Timeout
import org.openjdk.jmh.annotations.Warmup
import java.util.concurrent.TimeUnit.MICROSECONDS
import java.util.concurrent.TimeUnit.SECONDS

@Fork(2)
@Warmup(iterations = 2, time = 5, timeUnit = SECONDS)
@Measurement(iterations = 5, time = 5, timeUnit = SECONDS)
@Timeout(time = 6, timeUnit = SECONDS)
@BenchmarkMode(Throughput)
@OutputTimeUnit(MICROSECONDS)
@State(Scope.Benchmark)
open class NumberGeneratorsBenchmark {
    private lateinit var seqNumberGenerator: NumberGenerator
    private lateinit var concurrentSeqNumberGenerator: NumberGenerator

    @Setup(Level.Trial)
    open fun setup() {
        seqNumberGenerator = NumberGenerator.seq()
        concurrentSeqNumberGenerator = NumberGenerator.concurrentSeq()
    }

    @Benchmark
    fun seqNumberGenerator() {
        seqNumberGenerator.get()
    }

    @Benchmark
    fun concurrentSeqNumberGenerator() {
        concurrentSeqNumberGenerator.get()
    }
}
