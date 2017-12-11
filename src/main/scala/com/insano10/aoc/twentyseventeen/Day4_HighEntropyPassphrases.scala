package com.insano10.aoc.twentyseventeen

class Day4_HighEntropyPassphrases {


  /*
  how many of the entries are valid passphrases where a passphrase cannot contain the same
  string twice (separated by a space)
   */
  def countValidPassphrases(phrases: List[String]): Int = {

    phrases.foldLeft(0)((acc, phrase) => {

      val wordArray = phrase.split(" ")
      val uniqueWords = wordArray.toSet

      if(wordArray.length == uniqueWords.size) {
        acc + 1
      } else {
        acc
      }
    })
  }

  /*
  how many of the entries are valid passphrases where a passphrase cannot contain the same
  string twice or strings that are anagrams of each other (separated by a space)
   */
  def countValidPassphrasesPart2(phrases: List[String]): Int = {

    phrases.foldLeft(0)((acc, phrase) => {

      val wordArray = phrase.split(" ").map(_.sorted)
      val uniqueWords = wordArray.toSet

      if(wordArray.length == uniqueWords.size) {
        acc + 1
      } else {
        acc
      }
    })
  }
}
