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
