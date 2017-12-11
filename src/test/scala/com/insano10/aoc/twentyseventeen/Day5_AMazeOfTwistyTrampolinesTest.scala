package com.insano10.aoc.twentyseventeen

import org.scalatest.FunSpec

import scala.io.Source

class Day5_AMazeOfTwistyTrampolinesTest extends FunSpec {

  val solution = new Day5_AMazeOfTwistyTrampolines

  describe("Part 1") {

    it("should solve example 1") {
      assert(solution.countStepsToEscapeMaze(List(0, 3, 0, 1, -3)) === 5)
    }

    it("should solve input question") {

      val input = Source.fromFile("src/test/resources/Day5_Input.txt").getLines().map(_.toInt).toList

      assert(solution.countStepsToEscapeMaze(input) === 336905)
    }
  }

  describe("Part 2") {

    it("should solve example 1") {
      assert(solution.countStepsToEscapeMazePart2(List(0, 3, 0, 1, -3)) === 10)
    }

    it("should solve input question") {

      val input = Source.fromFile("src/test/resources/Day5_Input.txt").getLines().map(_.toInt).toList

      assert(solution.countStepsToEscapeMazePart2(input) === 21985262)
    }
  }

}
