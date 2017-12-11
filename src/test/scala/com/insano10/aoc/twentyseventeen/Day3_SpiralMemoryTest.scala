package com.insano10.aoc.twentyseventeen

import org.scalatest.FunSpec

class Day3_SpiralMemoryTest extends FunSpec {

  val solution = new Day3_SpiralMemory

  describe("Part 1") {

    it("should solve example 1") {
      assert(solution.getDistanceToDataPort(1) === 0)
    }

    it("should solve example 2") {
      assert(solution.getDistanceToDataPort(12) === 3)
    }

    it("should solve example 3") {
      assert(solution.getDistanceToDataPort(23) === 2)
    }

    it("should solve example 4") {
      assert(solution.getDistanceToDataPort(1024) === 31)
    }

    it("should solve the input question") {
      assert(solution.getDistanceToDataPort(368078) === 371)
    }
  }

  describe("Part 2") {

    it("should solve the input question") {
      assert(solution.getStressTestValueLargerThan(368078) === 369601)
    }
  }
}
