package com.sky.discovery.jvm.jmh

import java.util.concurrent.atomic.AtomicLong

sealed class UniqueNumberGenerator {
    companion object {
        @JvmStatic
        @JvmOverloads
        fun seq(start: Long = 0): UniqueNumberGenerator = SequentialNumberGenerator(start)

        @JvmStatic
        @JvmOverloads
        fun concurrentSeq(start: Long = 0): UniqueNumberGenerator = ConcurrentSequentialNumberGenerator(start)
    }

    abstract fun get(): Long
}

private class SequentialNumberGenerator(start: Long) : UniqueNumberGenerator() {
    @Volatile
    private var current: Long = start

    override fun get(): Long = current.also {
        current = current.inc()
    }
}

private class ConcurrentSequentialNumberGenerator(start: Long) : UniqueNumberGenerator() {
    private val current = AtomicLong(start)

    override fun get(): Long = current.getAndIncrement()
}
