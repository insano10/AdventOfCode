package com.insano10.aoc.twentyseventeen

import scala.annotation.tailrec

class Day6_MemoryReallocation {

  /*

  Take the bank with the highest number of blocks and spread them round robin over the other banks
  Repeat until a configuration encountered before is reached

  0, 2, 7, 0
  2, 4, 1, 2
  3, 1, 2, 3
  0, 2, 3, 4
  1, 3, 4, 1
  2, 4, 1, 2
   */
  def countMovesTillLoop(initialMemoryBlocks: Array[Int]): Int = {

    def getBankToRedistribute(banks: Array[Int]) = banks.zipWithIndex.maxBy(_._1)._2

    def redistrubute(banks: Array[Int], bankIdx: Int): Unit = {

      val totalMemory = banks(bankIdx)
      banks(bankIdx) = 0

      (1 to totalMemory).foreach { memoryNum =>
        val nextIdx = (bankIdx + memoryNum) % banks.length
        banks(nextIdx) = banks(nextIdx) + 1
      }
    }

    @tailrec
    def distributeTillLoop(banks: Array[Int], seenDistributions: Set[Array[Int]], totalMoves: Int): Int = {

      val currentBankDistribution = banks.clone()

      if(seenDistributions.exists(_.deep == currentBankDistribution.deep)) {
        totalMoves
      } else {
        redistrubute(banks, getBankToRedistribute(banks))
        distributeTillLoop(banks, seenDistributions ++ Set(currentBankDistribution), totalMoves + 1)
      }
    }

    distributeTillLoop(initialMemoryBlocks, Set(), 0)
  }

}
