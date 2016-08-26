// 4.1 Set up a map of prices for a number of gizmos that you covet and a price. Then create another map with 10 % discount.

val myMap = Map("Ball" -> 99, "Bat" -> 199, "Church" -> 300); myMap.mapValues(_ * 0.9);

// 4.2 Write a program that reads words from a file. Use a mutable map to count how often a word appears

val in = new java.util.Scanner(new java.io.File("chapter 3.scala"))

var map = scala.collection.mutable.Map[String, Int]();
while (in.hasNext()) {
  val word = in.next(); if (map.contains(word)) map(word) += 1 else map(word) = 1
}

for ((k, v) <- map) println(k + ": " + v);

// 4.3 Repeat the same exercise but with an immutable map.

val in = new java.util.Scanner(new java.io.File("chapter 3.scala"))

var map = Map[String, Int]();
while (in.hasNext()) {
  val word = in.next(); map = if (map.contains(word)) map + (word -> (map(word) + 1)) else map + (word -> 1)
}

for ((k, v) <- map) println(k + ": " + v);

// 4.4 Repeat the same exercise but with a sorted map.

val in = new java.util.Scanner(new java.io.File("chapter 3.scala"))

var map = SortedMap[String, Int]();
while (in.hasNext()) {
  val word = in.next(); map = if (map.contains(word)) map + (word -> (map(word) + 1)) else map + (word -> 1)
}

for ((k, v) <- map) println(k + ": " + v);

// 4.5 Repeat the same exercise but with a java.util.TreeMap.

import scala.collection.JavaConversions.mapAsScalaMap

val in = new java.util.Scanner(new java.io.File("chapter 3.scala"))

val map : scala.collection.mutable.Map[String, Int] = new java.util.TreeMap[String, Int]();
while (in.hasNext()) {
  val word = in.next(); if (map.contains(word)) map(word) += 1 else map(word) = 1
  // val word = in.next(); map = if (map.contains(word)) map + (word -> (map(word) + 1)) else map + (word -> 1)
  // val word = in.next(); if(map.containsKey(word)) map.put( word, map.get(word) + 1 ) else map.put( word, 1 )
}

for ((k, v) <- map) println(k + ": " + v);

// 4.6 Define a linked hash map that maps "Monday" to "java.util.Calendar.MONDAY" ...

val map = scala.collection.mutable.LinkedHashMap[String, Int]()

map.put("Monday", java.util.Calendar.MONDAY)
map.put("Tuesday", java.util.Calendar.TUESDAY)
map.put("Wednesday", java.util.Calendar.WEDNESDAY)
map.put("Thursday", java.util.Calendar.THURSDAY)
map.put("Friday", java.util.Calendar.FRIDAY)
map.put("Saturday", java.util.Calendar.SATURDAY)
map.put("Sunday", java.util.Calendar.SUNDAY)

for ((k, v) <- map) println(k + ": " + v);

// 4.7 Print a table of all Java properties, like this:

import scala.collection.JavaConversions.propertiesAsScalaMap

implicit def mutable2ImmutableMap(x: scala.collection.mutable.Map[String, String]) =
  x.toMap

val properties : scala.collection.mutable.Map[String, String] = System.getProperties()

val maxKeyLength = properties.keys.map(_.size).max

def printTable(table: Map[String, String], keyLength : Int) {
  for((k, v) <- table) {
    val printString = "%"+keyLength+"s | %s" format (k, v)
    println(printString)
  }
}

printTable(properties, maxKeyLength)

// 4.8 Write a function minmax(values: Array[Int]) that returns a pair containing the smallest and largest values in the array.

def minmax(values: Array[Int]) = Pair(values.min, values.max)

// 4.9 Write a function Iteqgt(values: Array[Int], v : Int) that returns a triple containing the counts of values less than v, equals to v and greater than v.

def lteqgt(values: Array[Int], v: Int) = {
  var lt=0; var eq = 0; var gt = 0;
  values.foreach( {
  case x: Int if (x > v) => gt+=1
  case x: Int if (x == v)  => eq += 1
  case x: Int if (x < v)  => lt += 1
  })
  (lt, eq, gt)
}

// 4.10 What happens when you zip two string together like "Hello".zip("World") ?

"Hello".zip("World")

// The response becomes a vector with pairs. Plausible use case could be two string contains wins and losses for two teams, that want to be compared.



