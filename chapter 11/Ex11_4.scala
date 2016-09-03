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