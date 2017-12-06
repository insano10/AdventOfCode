package com.insano10.aoc.twentyseventeen

import org.scalatest.FlatSpec

class Day3_SpiralMemoryTest extends FlatSpec {

  val solution = new Day3_SpiralMemory

  it should "solve example 1" in {
    assert(solution.getDistanceToDataPort(1) === 0)
  }

  it should "solve example 2" in {
    assert(solution.getDistanceToDataPort(12) === 3)
  }

  it should "solve example 3" in {
    assert(solution.getDistanceToDataPort(23) === 2)
  }

  it should "solve example 4" in {
    assert(solution.getDistanceToDataPort(1024) === 31)
  }

  it should "solve the input question" in {
    assert(solution.getDistanceToDataPort(368078) === 371)
  }
}
