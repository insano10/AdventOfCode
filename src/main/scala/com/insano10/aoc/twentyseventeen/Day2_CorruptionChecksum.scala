package com.insano10.aoc.twentyseventeen

class Day2_CorruptionChecksum {

  /*
  for each row find the difference between the min and max value, then sum for checksum
   */
  def getChecksum(spreadsheet: Array[Array[Int]]): Long = {

    spreadsheet.foldLeft(0)((acc, row) => {
      acc + (row.max - row.min)
    })
  }

  /*
  find the only 2 evenly divisible numbers on each row, divide them and sum all for the checksum
   */
  def getChecksumPart2(spreadsheet: Array[Array[Int]]): Option[Long] = {

    def getEvenDivisorFromRow(row: Array[Int]): Option[Long] = {

      row.toSet
        .subsets(2)
        .flatMap(set => Set((set.head, set.tail.head), (set.tail.head, set.head)))
        .find(pair => pair._1 % pair._2 == 0)
        .map(pair => pair._1 / pair._2)
    }

    spreadsheet
      .map(row => getEvenDivisorFromRow(row))
      .reduce { (a, b) =>
        (a, b) match {
          case (Some(divisorA), Some(divisorB)) => Some(divisorA + divisorB)
          case _ => None
        }
      }
  }
}
