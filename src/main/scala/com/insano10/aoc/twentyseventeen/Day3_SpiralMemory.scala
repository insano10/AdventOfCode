package com.insano10.aoc.twentyseventeen

import scala.annotation.tailrec

class Day3_SpiralMemory {

  /*

  17  16  15  14  13
  18   5   4   3  12
  19   6   1   2  11
  20   7   8   9  10
  21  22  23---> ...

   */

  private def setGridLocation(grid: Array[Array[Long]], x: Int, y: Int, locNum: Long): Unit = {
    grid(x)(y) = locNum
  }

  @tailrec
  private def traverseMemory(grid: Array[Array[Long]],
                             targetLocation: Long,
                             currentLocNum: Long,
                             currentLoc: (Int, Int),
                             horizSteps: Int,
                             vertSteps: Int,
                             horizDirection: Int,
                             vertDirection: Int,
                             vectorToLocOne: (Int, Int)): Int = {

    if (currentLocNum == targetLocation) {
      Math.abs(vectorToLocOne._1) + Math.abs(vectorToLocOne._2)
    } else {

      val horizStepsToTake = Math.min(targetLocation - currentLocNum, horizSteps).intValue()
      val vertStepsToTake = Math.min(targetLocation - currentLocNum, vertSteps).intValue()

      (1 to horizStepsToTake).foreach { num =>
        setGridLocation(grid, currentLoc._1 + (horizDirection * num), currentLoc._2, currentLocNum + 1)
      }

      (1 to vertStepsToTake).foreach { num =>
        setGridLocation(grid, currentLoc._1, currentLoc._2 + (vertDirection * num), currentLocNum + 1)
      }

      traverseMemory(
        grid = grid,
        targetLocation = targetLocation,
        currentLocNum = currentLocNum + horizStepsToTake.abs + vertStepsToTake.abs,
        currentLoc = (currentLoc._1 + (horizStepsToTake * horizDirection), currentLoc._2 + (vertStepsToTake * vertDirection)),
        horizSteps = if (horizSteps == 0) vertSteps + 1 else 0,
        vertSteps = if (horizSteps == 0) 0 else horizSteps,
        horizDirection = if (horizSteps == 0) -horizDirection else horizDirection,
        vertDirection = if (horizSteps == 0) vertDirection else -vertDirection,
        vectorToLocOne = (vectorToLocOne._1 + (horizStepsToTake * horizDirection), vectorToLocOne._2 + (vertStepsToTake * vertDirection))
      )
    }
  }

  def getDistanceToDataPort(targetLocation: Long): Int = {

    val gridSize = Math.sqrt(targetLocation).ceil.toInt + 1
    val grid = Array.ofDim[Long](gridSize, gridSize)
    val startingLoc = (gridSize / 2, gridSize / 2)

    setGridLocation(grid, startingLoc._1, startingLoc._2, 1)

    traverseMemory(
      grid = grid,
      targetLocation = targetLocation,
      currentLocNum = 1,
      currentLoc = startingLoc,
      horizSteps = 1,
      vertSteps = 0,
      horizDirection = 1,
      vertDirection = 1,
      vectorToLocOne = (0, 0)
    )
  }
}
