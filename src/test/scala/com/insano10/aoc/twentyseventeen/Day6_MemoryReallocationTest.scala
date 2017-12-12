package com.insano10.aoc.twentyseventeen

import org.scalatest.FunSpec

class Day6_MemoryReallocationTest extends FunSpec {

  val solution = new Day6_MemoryReallocation

  describe("Part 1") {

    it("should solve example 1") {
      assert(solution.countMovesTillLoop(Array(0, 2, 7, 0)) === 5)
    }

    it("should solve input question") {

      assert(solution.countMovesTillLoop(Array(4, 1, 15, 12, 0, 9, 9, 5, 5, 8, 7, 3, 14, 5, 12, 3)) === 6681)
    }
  }
//
//  describe("Part 2") {
//
//    it("should solve example 1") {
//      assert(solution.countStepsToEscapeMazePart2(Array(0, 3, 0, 1, -3)) === 10)
//    }
//
//    it("should solve input question") {
//
//      val input = Source.fromFile("src/test/resources/Day5_Input.txt").getLines().map(_.toInt).toArray
//
//      assert(solution.countStepsToEscapeMazePart2(input) === 21985262)
//    }
//  }

}
