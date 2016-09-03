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