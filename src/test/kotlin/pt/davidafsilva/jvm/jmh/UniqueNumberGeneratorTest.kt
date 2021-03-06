package pt.davidafsilva.jvm.jmh

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.DescribeSpec

class UniqueNumberGeneratorTest : DescribeSpec({

    describe("an unique number generator factory") {
        val factory = NumberGenerator

        describe("creating an sequential generator") {

            it("does not throw any exceptions without a start value specified") {
                shouldNotThrowAny { factory.seq() }
            }

            it("does not throw any exceptions with a start value specified") {
                shouldNotThrowAny { factory.seq(start = 111) }
            }
        }

        describe("creating a concurrent sequential generator") {

            it("does not throw any exceptions without a start value specified") {
                shouldNotThrowAny { factory.concurrentSeq() }
            }

            it("does not throw any exceptions with a start value specified") {
                shouldNotThrowAny { factory.concurrentSeq(start = 111) }
            }
        }
    }
})
