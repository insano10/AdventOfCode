package com.insano10.aoc.twentyseventeen

import org.scalatest.FunSpec

import scala.io.Source

class Day4_HighEntropyPassphrasesTest extends FunSpec {

  val solution = new Day4_HighEntropyPassphrases

  describe("Part 1") {

    it("should solve example 1") {
      assert(solution.countValidPassphrases(List("aa bb cc dd ee")) === 1)
    }

    it("should solve example 2") {
      assert(solution.countValidPassphrases(List("aa bb cc dd aa")) === 0)
    }

    it("should solve example 3") {
      assert(solution.countValidPassphrases(List("aa bb cc dd aaa")) === 1)
    }

    it("should solve input question") {

      val input = Source.fromFile("src/test/resources/Day4_Input.txt").getLines().toList

      assert(solution.countValidPassphrases(input) === 466)
    }
  }

  describe("Part 2") {

    it("should solve example 1") {
      assert(solution.countValidPassphrasesPart2(List("abcde fghij")) === 1)
    }

    it("should solve example 2") {
      assert(solution.countValidPassphrasesPart2(List("abcde xyz ecdab")) === 0)
    }

    it("should solve example 3") {
      assert(solution.countValidPassphrasesPart2(List("a ab abc abd abf abj")) === 1)
    }

    it("should solve example 4") {
      assert(solution.countValidPassphrasesPart2(List("iiii oiii ooii oooi oooo")) === 1)
    }

    it("should solve example 5") {
      assert(solution.countValidPassphrasesPart2(List("oiii ioii iioi iiio")) === 0)
    }

    it("should solve input question") {

      val input = Source.fromFile("src/test/resources/Day4_Input.txt").getLines().toList

      assert(solution.countValidPassphrasesPart2(input) === 251)
    }
  }
}
