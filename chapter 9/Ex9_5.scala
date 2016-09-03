// 9.5 Write a Scala program that writes the powers of 2 and their receiprocals to a file, with the exponent ranging from 0 to 20. Line up the columns.

object Ex9_5 extends App {

  val allPowers = for(i <- 1 to 20) yield BigInt(2).pow(i)

  val maxStringSize = allPowers.max.toString.size 

  for(i <- allPowers) println("%" +maxStringSize +"d %f" format(i, 1/i)) 

}