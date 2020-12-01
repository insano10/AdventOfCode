package com.insano10.aoc.twentyseventeen

import scala.annotation.tailrec

class Day1_InverseCapture {

  private def getValue(num1: Int, num2: Int): Long = {

    if(num1 == num2) {
      num1
    } else {
      0
    }
  }

  /*
  shuffle digits between left/right list to compare each digit with neighbour
   */
  def getCaptureSum(capture: String): Long = {

    @tailrec
    def sumAndRecurse(leftInput: List[Int], rightInput: List[Int], totalSum: Long): Long = {

      rightInput match {
        case Nil => totalSum + getValue(leftInput.head, leftInput.last)
        case x :: xs => sumAndRecurse(leftInput ++ List(x), xs, totalSum + getValue(leftInput.last, x))
      }
    }

    val captureDigits = capture.toList.map(_.asDigit)
    sumAndRecurse(List(captureDigits.head), captureDigits.tail, 0)
  }

  /*
  duplicate the digits into one list to avoid wrapping then move through comparing with the offset digit
   */
  def getCaptureSumPart2(capture: String): Long = {

    @tailrec
    def sumAndRecurse(input: List[Int], comparisonOffset: Int, digitsRemaining: Int, totalSum: Long): Long = {

      val nextVal = getValue(input.head, input.drop(comparisonOffset).head)

      if(digitsRemaining == 1) {
        totalSum + nextVal
      } else {
        sumAndRecurse(input.tail, comparisonOffset, digitsRemaining - 1, totalSum + nextVal)
      }
    }

    val numDigits = capture.length
    val comparisonOffset = numDigits/2
    val doubleCaptureDigits = (capture ++ capture).toList.map(_.asDigit)

    sumAndRecurse(doubleCaptureDigits, comparisonOffset, numDigits, 0)
  }
}
