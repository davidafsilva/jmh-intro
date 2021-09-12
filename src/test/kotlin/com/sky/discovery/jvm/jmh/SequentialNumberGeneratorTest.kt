package com.sky.discovery.jvm.jmh

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.longs.shouldBeExactly

class SequentialNumberGeneratorTest : DescribeSpec({

    describe("a sequential number generator") {
        val initialValue = 99L
        val generator = UniqueNumberGenerator.seq(start = initialValue)

        describe("generating a number") {
            it("does return the default initial value") {
                generator.get() shouldBeExactly initialValue
            }

            it("does increment and properly return the next value") {
                generator.get() shouldBeExactly initialValue + 1
            }
        }
    }
})
