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