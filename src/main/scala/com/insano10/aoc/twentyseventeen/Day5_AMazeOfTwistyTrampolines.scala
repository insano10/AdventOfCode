package com.insano10.aoc.twentyseventeen

import scala.annotation.tailrec

class Day5_AMazeOfTwistyTrampolines {

  /*
  Follow the jumps in the list to instructions ahead and behind you
  Increment the current instruction value after execution
  How many steps to get out of the bounds of the list?

  (0) 3  0  1  -3  - before we have taken any steps.
  (1) 3  0  1  -3  - jump with offset 0 (that is, don't jump at all). Fortunately, the instruction is then incremented to 1.
  2 (3) 0  1  -3  - step forward because of the instruction we just modified. The first instruction is incremented again, now to 2.
  2  4  0  1 (-3) - jump all the way to the end; leave a 4 behind.
  2 (4) 0  1  -2  - go back to where we just were; increment -3 to -2.
  2  5  0  1  -2  - jump 4 steps forward, escaping the maze.

   */
  def countStepsToEscapeMaze(jumpList: List[Int]): Int = {

    val maxIdx = jumpList.length - 1

    @tailrec
    def doJump(jumpList: List[Int], idx: Int, totalSteps: Int): Int = {

      if(idx > maxIdx) {
        totalSteps
      } else {

        val currentJump = jumpList(idx)
        doJump(
          jumpList = jumpList.take(idx) ++ List(currentJump + 1) ++ jumpList.drop(idx + 1),
          idx = idx + currentJump,
          totalSteps = totalSteps + 1
        )
      }
    }

    doJump(jumpList, 0, 0)
  }

  /*
    Same as above but now after each jump, if the offset was three or more, instead decrease it by 1.
     Otherwise, increase it by 1 as before.
   */
  def countStepsToEscapeMazePart2(jumpList: List[Int]): Long = {

    val maxIdx = jumpList.length - 1

    @tailrec
    def doJump(jumpList: List[Int], idx: Int, totalSteps: Long): Long = {

      if(idx > maxIdx) {
        totalSteps
      } else {

        val currentJump = jumpList(idx)
        val updatedOffset = if(currentJump >= 3) currentJump - 1 else currentJump + 1
        doJump(
          jumpList = jumpList.take(idx) ++ List(updatedOffset) ++ jumpList.drop(idx + 1),
          idx = idx + currentJump,
          totalSteps = totalSteps + 1
        )
      }
    }

    doJump(jumpList, 0, 0)
  }
}
