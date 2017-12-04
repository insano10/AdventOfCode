package com.insano10.aoc

class Day2_CorruptionChecksum {

  def getChecksum(spreadsheet: Array[Array[Int]]): Long = {

    spreadsheet.foldLeft(0)((acc, row) => {
      acc + (row.max - row.min)
    })
  }
}
