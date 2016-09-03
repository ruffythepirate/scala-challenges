object Ex9_2 extends App {
  assert(args.size >= 2)

  val n = args(1).toInt

  val input = io.Source.fromFile(args(0))

  
  try {
    allLines = input.getLines.toArray
    val transformedLines = allLines.map(transformLine(_, n))

    writeLines(args(0), transformedLines) 
 
  } finally {
    input close
  }



  def transformLine(line : String, n : Int) {
    val newLine = collection.mutable.ArrayBuffer[Char]()
    var charsInString = 0
    for(c <- line) {
      charsInString += 1
      c match {
        case '\t' => //newLine ++ ("".format("%" + (n - (charsInString % n)) + "s")
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