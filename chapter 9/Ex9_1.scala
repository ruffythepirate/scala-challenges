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