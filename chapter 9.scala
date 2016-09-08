// 9.1 Write a Scala code snippet that reverses the lines in a file (making the last line the first one, and so on.)

object Ex9_1 extends App {
  assert(args.size > 0)

  val input = io.Source.fromFile(args(0))
  try {
    val reversedLines = input.getLines.toArray.reverse

    writeLines(reversedLines)

  } finally {
    input close
  }

  def writeLines(lines: Seq[String]) {
    val out = new java.io.PrintWriter("reversed.txt")
    try {
      for (l <- lines) out.println(l)
    } finally {
      out close
    }
  }
}

// 9.2 Write a Scala program that reads a file with tabs, replaces each tab with spaces so that tab stops are at n-column boundaries, and writes the result to the same file.

object Ex9_2 extends App {
  assert(args.size >= 2)

  val n = args(1).toInt

  val input = io.Source.fromFile(args(0))

  lazy val allLines: Array[String]
  try {
    allLines = input.getLines.toArray
  } finally {
    input close
  }
  val transformedLines = allLines.map(transformLine(_, n))

  writeLines(args(0), transformedLines)


  def transformLine(line : String, n : Int) {
    val newLine = collection.mutable.ArrayBuffer[Char]()
    var charsInString = 0
    for(c <- line) {
      charsInString += 1
      c match {
        case '\t' => { newLine ++ ("".format("%" + (n - (charsInString % n)) + "s"))}
        case _ => newLine.append(_)
      }
    }
  }

  def writeLines(filename: String, lines: Seq[String]) {
    val out = new java.io.PrintWriter("reversed.txt")
    try {
      for (l <- lines) out.println(l)
    } finally {
      out close
    }
  }
}

// 9.3 Write a Scala code snippet that reads a file and prints all words with more than 12 characters to the console. Extra credit if you can do this in a single line.

io.Source.fromFile("09_02").getLines.foreach(l => l.split("\\s").filter(_.size > 12).foreach(println(_)))

// 9.4 Write a Scala program that reads a text file containing only floating-point numbers. Print the sum, average, maximum and minimum of the numbers in the file.

object Ex9_4 extends App {

  assert(args.size >= 1)

  val allDoubles = new collection.mutable.ArrayBuffer[Double]()

  val floatRegEx = """\d+([.]\d+)?""".r

  for(line <- io.Source.fromFile(args(0)).getLines ) {
    val allMatches = floatRegEx.findAllIn(line)
    for(myMatch <- allMatches) {
      allDoubles.append( myMatch.toDouble )      
    }
  }

  if(allDoubles.size > 0)
    println("average: %.2f, sum: %.2f, max: %.2f, min: %.2f" format(allDoubles.sum / allDoubles.size, allDoubles.sum, allDoubles.max, allDoubles.min))
    else 
    println("Found no floats!")
}

// 9.5 Write a Scala program that writes the powers of 2 and their receiprocals to a file, with the exponent ranging from 0 to 20. Line up the columns.

object Ex9_5 extends App {

  val allPowers = for(i <- 1 to 20) yield BigInt(2).pow(i)

  val maxStringSize = allPowers.max.toString.size 

  for(i <- allPowers) println("%" +maxStringSize +"d %f" format(i, 1/i)) 
}

 // 9.6 Write a regular expression searching for quoted strings "like this, maybe with \" or \\" in Java or C++ program. Write a Scala program that prints out all such strings in a source file.

"""""(.*?)(?:[^\\]"|[^\\](?:\\\\)+")""".r.unanchored // This expression works for \" and \\"

// 9.7 Write a Scala program that reads a text file and prints all tokens in the file that are not floating-point numbers. Use a regular expression.

object Ex9_7 extends App {

  assert( args.size >= 1)

  val allLines = io.Source.fromFile(args(0)).getLines

  for(line <- allLines) {
    for(token <- findTokens(line))
      println(token)
  }

  def findTokens(line: String) = {
    """\w+""".r.findAllIn(line).toArray.filter(!_.matches("""\d+\.\d+"""))
  }
}


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

// 9.9 Write a Scala program that counts how many files with .class extension are in a given directory and its subdirectories.
object Ex9_9 extends App {

  import java.io.File

var currentDir = if(args.size >= 1) new java.io.File(args(0)) else new java.io.File(".")

      val allDirs = subdirs(currentDir) :+ currentDir
      val numberOfDirs = allDirs.size
      println(s"Will be looking in $numberOfDirs dirs...")
      val numberOfClassFiles = allDirs.flatMap(getClassFiles(_)).size
      println(s"There are $numberOfClassFiles class files in the directory...")

  def subdirs(dir: File): Array[File] = {
    val children = dir.listFiles.filter(_.isDirectory)
    children ++: children.flatMap(subdirs _)
  }

  def getClassFiles(dir: File) = {
    dir.listFiles.filter(_.isFile).filter(isClassFile(_))
  }

  def isClassFile(dir: File) = {
    dir getName() endsWith(".class")
  }

}

// 9.10 Expand the example with the seralizable Person class that stores a collection of friends. Construct a few Person objects, make som of the friends of naother, and then save an Array[Person] to a file. Read the array back in and verify that the friend relations are intact.

object Ex9_10 extends App {

  val filename = "person.txt"

  def serializePersonsToFile( persons : Array[Person], filename : String) {
    val out = new java.io.ObjectOutputStream(new java.io.FileOutputStream(filename))
    out.writeObject(persons)
    out close
  }

  def deserializePerson(filename: String) = {
    val in = new java.io.ObjectInputStream(new java.io.FileInputStream(filename))
    val people = in.readObject().asInstanceOf[Array[Person]]
    in.close()
    people
  }

  @SerialVersionUID(1L) case class Person(val name: String) extends Serializable {
    private val friends = new collection.mutable.ArrayBuffer[Person]()

    def addFriend(friend: Person) {
      friends :+ friend
    }
  }
}



