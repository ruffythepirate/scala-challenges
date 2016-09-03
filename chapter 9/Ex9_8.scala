// 9.8 Write a Scala program that prints the src attributes of all img tags of a web page. Use regular expressions and groups.

object Ex9_8 extends App {

  if (args.size >= 1) {
    val file = io.Source.fromFile(args(0))
    val allLines = file.getLines
    val urls = getUrls(allLines)
    for (url <- urls) println(url)

  } else {
    val testExample1 = Array("""<img src="an url" other attributes/> <a src="other url"></a>""")
    val testExample2 = Array("""<img otherattribute="other value" src="an url" other attributes/> <a src="other url"></a>""")
    val testExample3 = Array("""<img otherattribute="other value" src="an url" other attributes/> <img otherattribute="other value" src="an url" other attributes/> <a src="other url"></a>""")
    assert(getUrls(testExample1).size == 1)
    assert(getUrls(testExample2).size == 1)
    assert(getUrls(testExample3).size == 2)
    assert(getUrls(testExample1 ++ testExample2).size == 2)
  }

  val imgSourceRegEx = """<img .*?src=\"(.*?)\".*?/>""".r.unanchored

  def getUrlsForLine(line : String) = {
    val mi = imgSourceRegEx findAllIn line
     mi map(_ => mi group 1)
      
  }

  def getUrls(allLines : Array[String]) = {
    val urls = new collection.mutable.ArrayBuffer[String]()
    for (line <- allLines) {
      urls ++= getUrlsForLine(line)
    }
    urls.toArray
  }
}