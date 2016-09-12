/**
* 11.1 According to the precedence rules, how are 3 + 4 -> 5 and 3 -> 4 + 5 evaluated ?
**/

// Because - and + have same precendence, it is evaluated from left to right (3 + 4) -> 5 or (3 -> 4) + 5

/**
* 11.2 The BigInt class has a pow method, not an operator. Why didn't the Scala library designers choose ** (as in Fortran) or ^ (as in Pascal) for a Power operator?
**/

// It is a bad idea, since ** would get the same precedence as * or /, which would mean that 3 * 2 ** 4 becomes 6 ** 4, not 3 * 16

/**
* 11.3 Implement the Fraction class with operations + - * /. 
* Normalize fractions, for example turning 15/-6 into -5/2.
* Divide by the greatest common divisor, like this:
*

 class Fraction(n : Int, d: Int) {
  private val num: Int = if(d == 0) 1 else n * sign(d) / gcd(n, d)

  private val den: Int = if(d == 0) 0 else d * sign(d) / gcd(n, d)

  override def toString = num + "/" + den 

  def sign(a: Int) = if(a > 0) 1 else if(a < 0) -1 else 0

  def gcd (a: Int, b : Int) : Int = if(b == 0) math.abs(a) else gcd(b, a % b) 
}
**/

object Fraction {
  def apply(n: Int, d: Int) = new Fraction(n, d)
}

 class Fraction(n : Int, d: Int) {
  private val num: Int = if(d == 0) 1 else n * sign(d) / gcd(n, d)

  private val den: Int = if(d == 0) 0 else d * sign(d) / gcd(n, d)

  override def toString = num + "/" + den 

  def sign(a: Int) = if(a > 0) 1 else if(a < 0) -1 else 0

  def gcd (a: Int, b : Int) : Int = if(b == 0) math.abs(a) else gcd(b, a % b) 

  def +(other: Fraction) = new Fraction(num * other.den + den * other.num, den * other.den)

  def -(other: Fraction) = new Fraction(num * other.den - den * other.num, den * other.den)
  
}

/**
* 11.4 Implement a class Money with fields for dollars and cents. 
* Supply +, - operators as well as comparison operators == and <.
* For example, Money(1, 75) + Money(0, 50) == Money(2, 25) should be true. 
* Should you also supply * and / operators? Why or why not?
**/

object Money {
  def apply(dollars: Int, cents: Int) = new Money(dollars, cents)
}

class Money(d: Int, c: Int) {

  private val total = d * 100 + c

  val cents = total % 100
  val dollars = total / 100

  def ==(other: Money) = dollars == other.dollars && cents == other.cents
  def +(other: Money) = Money(dollars + other.dollars, cents + other.cents)
  def -(other: Money) = Money(dollars - other.dollars, cents - other.cents)

  override def toString = "$%d.%02d" format(dollars, cents)
}

/**
* 11.5 Provide operators that construct an HTML table. For example,

Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, .NET"

should produce

<table><tr><td>Java</td><td>Scala</td></tr><tr><td>Gosling...
**/

object Table {
  def apply() = new Table(Array(Array[String]()))
}

class Table (content: Array[Array[String]]) {

  def |(column: String) : Table = {
    val clone = content.clone
    clone(content.size - 1) = clone(content.size - 1) :+ column
    new Table(clone)
  }

  def ||(row: String): Table = {
    new Table(content :+ Array(row) )
  }

  override def toString = {
    val stringBuild = new StringBuilder()
    stringBuild.append("<table>")
    for(row <- content) {
      stringBuild.append("<tr>")
      for(column <- row) {
        stringBuild.append("<td>")
        stringBuild.append(column)
        stringBuild.append("</td>")
      }
      stringBuild.append("</tr>")
    }
      stringBuild.append("</table>")
      stringBuild.toString
  }
}

/**
* 11.6 Provide a class ASCIIArt whose objects contain figures such as

  /\_/\
  (' ')
  ( - )
  | | |
  (_|_)

Supply operators for combining two ASCIIArt figures horizontally

or vertically. Choose operators with appropriate precendence.
**/


object Ex11_6 extends App {

  class AsciiArt (val content : Array[String]) {

    // Vertical addition
    def +(other : AsciiArt) = {
      new AsciiArt(content ++ other.content)
    }

    // Horizontal addition
    def *(other : AsciiArt) = {
      val verticalLength = math.max(content.size, other.content.size)
      val longestLength = content.map(_.size).max
      val newContent = extendContent(verticalLength).zip(other.extendContent(verticalLength))
          .map(t => t._1.padTo(longestLength + 1, ' ') + t._2)
      new AsciiArt(newContent)
    }

    private def extendContent(toLength: Int) = {
      val emptyStringArray = (1 to (toLength - content.size)).map(t => "").toArray
      content ++ emptyStringArray
    }

    override def toString = {
      content.mkString("\n")
    }
  }
}

/**
  11.7 Implement a class BitSequence that stores a sequence of 64 bits packed in a Long value. Supply apply and update operators to get and set anindividual bit.
**/

object Ex11_7 extends App {

  object BitSequence {
    def apply(firstBit : Int) = {
      val initalValue = 0l | 1 << firstBit
      
      new BitSequence (initalValue)
    }

    def apply() = {
      new BitSequence (0l)
    }
  }

  class BitSequence (private var _value : Long) {

    def value = _value

    def update(index : Int, newValue : Boolean) {

      _value = if(newValue) (_value | (1 << index)) else (_value ^ (1 << index))
    }
  }

  assert(BitSequence(0).value == 1)

  val myBitSequence = BitSequence(0)
  myBitSequence(1) = true

  assert(myBitSequence.value == 3)
}

/**
*
*  11.8 Provide a class Matrix - you can choose whether you want to implement 2 x 2 matrices of any size, square matrices of any size, or m x n matrices.
*       Supply operations + and *.
*       The latter should also work with scalars, for example mat*2. A single element should be accessible as mat(row, col)
**/

  object Matrix {

    def apply(rows: Int, cols: Int) = new Matrix(Array.ofDim[Int](rows,cols))
  }

  class Matrix (private val _content: Array[Array[Int]]) {

    private val _rows = _content.size
    private val _columns = if(_rows > 0) _content(0).size else 0
    
    def apply(row : Int, col: Int) = _content(row)(col)

    def update(row : Int, col: Int, value: Int) {_content(row)(col) = value}

    def *(other: Matrix) = {
      val otherRows = other._rows; val otherColumns = other._columns
      val rows = _rows; val columns = _columns
      if(_columns != other._rows) {
        throw new Exception(s"Invalid dimensions of matrices: [$otherRows, $otherColumns] cannot be multiplied to a matrix with dim [$rows, $columns]")
      }

      val newContent = Array.ofDim[Int](_rows, otherColumns)

      for(i <- 0 until rows) {
        for(j <- 0 until otherColumns) {
          for( k <- 0 until columns) {
            newContent(i)(j) += this(i, k) * other(k, j);
          }
        }
      }
      new Matrix(newContent)
    }

    def *(scalar : Int) = {
      new Matrix(_content.map(_.map(_*scalar)))
    }

    def +(other :Matrix) = {
      val otherRows = other._rows; val otherColumns = other._columns
      val rows = _rows; val columns = _columns

      if(_rows != other._rows || _columns != other._columns) {
        throw new Exception(s"Invalid dimensions of matrices: dim [$otherRows, $otherColumns] cannot be added to a matrix with dim [$rows, $columns]")
      }

      val newContent = Array.ofDim[Int](_rows, _columns)
      for(i <- 0 until _rows) {
        for(j <- 0 until _columns) {
          newContent(i)(j) = this(i, j) + other(i, j);
        }
      }

      new Matrix(newContent)
    }

    override def toString = "[" + _content.map(_.mkString(", ")).mkString("\n") + "]"

  }


// 11.9 Define an unapply operation for the RichFile class that extracts the file path, name, and extension.
// For example, the file /home/cay/readme.txt has path /home/cay, name readme and extension txt.

object Ex11_9 extends App {

  object RichFileExtractor {

    def unapply(file : java.io.File) : Option[(String, String, String)] = {

      val absolutePath = file.getAbsolutePath

      val pathTokens = absolutePath.split(java.io.File.separator)

      val fileNameWithExtension = pathTokens.last

      val fileName = 
        if(fileNameWithExtension.contains('.')) fileNameWithExtension.split('.').dropRight(1).mkString(".") else fileNameWithExtension

      val extension = 
        if(fileNameWithExtension.contains('.')) fileNameWithExtension.split('.').last else ""

      Some((pathTokens.dropRight(1).mkString(java.io.File.separator), fileName, extension.toString))
    }
  }  
}

// 11.10 Define an unapplySeq operation for the RichFile class that extracts all path segments. 
// For example, for the file /home/cay/readme.txt, you should produce a sequence of three segments: home, cay, and readme.txt.


