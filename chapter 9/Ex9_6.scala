// 9.6 Write a regular expression searching for quoted strings "like this, maybe with \" or \\" in Java or C++ program. Write a Scala program that prints out all such strings in a source file.

object Ex9_6 extends App {



  val regEx = "\"(.*?)[^\\]\"".r.unanchored
  def getQuotes(line: String) = {
    val mi = regEx findAllIn line
    mi map(_ => mi group 1)
  }

  val test1 = """This is "a line" with two "qoutes". """
  val test2 = """This is "a line\" with one \"qoutes". """
  val test3 = """This is "a line\\" with two \"qoutes". """

  assert(getQuotes(test1).size == 2)
  assert(getQuotes(test2).size == 1)
  assert(getQuotes(test3).size == 2)

}