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

  private def setGridValue(grid: Array[Array[Long]], x: Int, y: Int, value: Long): Unit = {
    grid(x)(y) = value
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
        setGridValue(grid, currentLoc._1 + (horizDirection * num), currentLoc._2, currentLocNum + 1)
      }

      (1 to vertStepsToTake).foreach { num =>
        setGridValue(grid, currentLoc._1, currentLoc._2 + (vertDirection * num), currentLocNum + 1)
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

    setGridValue(grid, startingLoc._1, startingLoc._2, 1)

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

  private def sumSurroundingValues(grid: Array[Array[Long]],
                                   currentLoc: (Int, Int)): Long = {

    def getGridValue(x: Int, y: Int): Long = {


      if(x < 0 || x > 19 || y < 0 || y > 19) {
        0
      } else {
        grid(x)(y)
      }
    }

    getGridValue(currentLoc._1 - 1, currentLoc._2) +
    getGridValue(currentLoc._1 - 1, currentLoc._2 + 1) +
    getGridValue(currentLoc._1, currentLoc._2 + 1) +
    getGridValue(currentLoc._1 + 1, currentLoc._2 + 1) +
    getGridValue(currentLoc._1 + 1, currentLoc._2) +
    getGridValue(currentLoc._1 + 1, currentLoc._2 - 1) +
    getGridValue(currentLoc._1, currentLoc._2 - 1) +
    getGridValue(currentLoc._1 - 1, currentLoc._2 - 1)
  }


  /*

  147  142  133  122   59
  304    5    4    2   57
  330   10    1    1   54
  351   11   23   25   26
  362  747  806--->   ...

   */

  @tailrec
  private def stressTestMemory(grid: Array[Array[Long]],
                             targetValue: Long,
                             currentLocValue: Long,
                             currentLoc: (Int, Int),
                             currentSideLength: Int,
                             horizSteps: Int,
                             vertSteps: Int,
                             horizDirection: Int,
                             vertDirection: Int): Long = {

    if (currentLocValue > targetValue) {
      currentLocValue
    } else {

      val newCurrentLoc = (currentLoc._1 + (horizDirection * Math.min(1, horizSteps)), currentLoc._2 + (vertDirection * Math.min(1, vertSteps)))
      val newCurrentLocValue = sumSurroundingValues(grid, newCurrentLoc)
      val newSideLength = if(horizSteps == 0 && vertSteps == 1) currentSideLength + 1 else currentSideLength
      val newHorizSteps = if(horizSteps > 1) horizSteps - 1 else if(vertSteps == 1) newSideLength else 0
      val newVertSteps = if(vertSteps > 1) vertSteps - 1 else if(horizSteps == 1) newSideLength else 0
      val newHorizDirection = if (newHorizSteps == 0 && vertSteps == 0) -horizDirection else horizDirection
      val newVertDirection = if (newVertSteps == 0 && horizSteps == 0) -vertDirection else vertDirection

      setGridValue(grid, newCurrentLoc._1, newCurrentLoc._2, newCurrentLocValue)

      stressTestMemory(
        grid = grid,
        targetValue = targetValue,
        currentLocValue = newCurrentLocValue,
        currentLoc = newCurrentLoc,
        currentSideLength = newSideLength,
        horizSteps = newHorizSteps,
        vertSteps = newVertSteps,
        horizDirection = newHorizDirection,
        vertDirection = newVertDirection
      )
    }
  }

  def getStressTestValueLargerThan(num: Long): Long = {

    val gridSize = 20
    val grid = Array.ofDim[Long](gridSize, gridSize)
    val startingLoc = (gridSize / 2, gridSize / 2)

    setGridValue(grid, startingLoc._1, startingLoc._2, 1)

    stressTestMemory(
      grid = grid,
      targetValue = num,
      currentLocValue = 1,
      currentLoc = startingLoc,
      currentSideLength = 1,
      horizSteps = 1,
      vertSteps = 0,
      horizDirection = 1,
      vertDirection = 1
    )
  }
}
