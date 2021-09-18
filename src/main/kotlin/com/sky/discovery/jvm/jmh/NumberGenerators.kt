package com.sky.discovery.jvm.jmh

import java.util.concurrent.atomic.AtomicLong

sealed class NumberGenerator {
    companion object {
        @JvmStatic
        @JvmOverloads
        fun seq(start: Long = 0): NumberGenerator = SequentialNumberGenerator(start)

        @JvmStatic
        @JvmOverloads
        fun concurrentSeq(start: Long = 0): NumberGenerator = ConcurrentSequentialNumberGenerator(start)
    }

    abstract fun get(): Long
}

private class SequentialNumberGenerator(start: Long) : NumberGenerator() {
    @Volatile
    private var current: Long = start

    override fun get(): Long = current.also {
        current = current.inc()
    }
}

private class ConcurrentSequentialNumberGenerator(start: Long) : NumberGenerator() {
    private val current = AtomicLong(start)

    override fun get(): Long = current.getAndIncrement()
}
