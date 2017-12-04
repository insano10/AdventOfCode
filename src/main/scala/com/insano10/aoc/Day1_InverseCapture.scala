package com.insano10.aoc

class Day1_InverseCapture {

  def getCaptureSum(capture: String): Long = {

    def getValue(num1: Int, num2: Int): Long = {

      if(num1 == num2) {
        num1
      } else {
        0
      }
    }

    def sumAndRecurse(leftInput: List[Int], rightInput: List[Int], totalSum: Long): Long = {

      rightInput match {
        case Nil => totalSum + getValue(leftInput.head, leftInput.last)
        case x :: xs => sumAndRecurse(leftInput ++ List(x), xs, totalSum + getValue(leftInput.last, x))
      }
    }

    val captureDigits = capture.toList.map(_.asDigit)
    sumAndRecurse(List(captureDigits.head), captureDigits.tail, 0)
  }
}
