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

