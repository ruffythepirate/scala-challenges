// 6.1 Write an object Conversions with methods inches to Centimeters, gallonsToLiters, and milesToKilometers

object Conversions {
  def inchesToCentimeters(inches : Double) = {
    inches * 2.54
  }

  def gallonsToLiters(gallons : Double) = {
    gallons * 3.79
  }

  def milesToKilometers(miles : Double) = {
    miles * 1.61
  }
}

//6.2 The preceding task was not very object oriented. Create a general superclass UnitConversion and extend it.

abstract class UnitConversion {
  def convert(x: Double) : Double
}

object InchesToCentimeters extends UnitConversion {
  def convert(inches : Double) = {
    inches * 2.54
  }
}

object GallonsToLiters extends UnitConversion {
  def convert(gallons : Double) = {
    gallons * 3.79
  }
}

object MilesToKilometers extends UnitConversion {
  def convert(miles : Double) = {
    miles * 1.61
  }
}

//6.3 Define an Origin object that extends java.awt.Point. Why is this actually not a good idea?

object Origin extends java.awt.Point {
  setLocation(0.0, 0.0)
}

//It is not a good idea because a Point is mutable, which creates a singleton object that can be edited from anywhere in the application.

//6.4 Define a Point class with a companion object so that you can construct Point instances as Point(3, 4), without using new.
class Point(val x : Double, val y : Double){
}

object Point {
  def apply(x : Double, y : Double) = {
    new Point(x, y)
  }
}

//6.5 Write a Scala application, using the App trait, that prins the command-line arguments in reverse order, seperated by spaces. For example, 
// scala Reverse Hello World should print World Hello
object Reverse extends App {
  println(args.reverse.mkString(" "))
}
// save in file Reverse.scala. Write scalac Reverse.scala then scala Reverse.

//6.6 Write an enumeration describing the four playing card suits so that the toString method returns their symbol.
object Suit extends Enumeration {
  type Suit = Value;
  val Clubs = Value(1, "♣")
  val Hearts = Value(2, "♥")
  val Diamonds = Value(3, "♦")
  val Spades = Value(4, "♠")
}

println("Check it out: " + Suit.Clubs)

//6.7 Implement a function that checks whether a card suit value from the preceiding exercise is red.
object Suit extends Enumeration {
  type Suit = Value;
  val Clubs = Value(1, "♣")
  val Hearts = Value(2, "♥")
  val Diamonds = Value(3, "♦")
  val Spades = Value(4, "♠")

  def isRed(cardSuit : Suit) = {
    cardSuit == Hearts || cardSuit == Diamonds
  }
}

//6.8 Write an enumeration describing the eight corners of the RGB color cube. As IDs, use the color values.

object RgbColor extends Enumeration {
  type RgbColor = Value

  val Black = Value(0x000000, "Black")
  val Blue = Value(0x0000ff, "Blue")
  val Green = Value(0x00ff00, "Green")
  val Tuqoise = Value(0x00ffff, "Tuqoise")
  val Red = Value(0xff0000, "Red")
  val Purple = Value(0xff00ff, "Purple")
  val Yellow = Value(0xffff00, "Yellow")
  val White = Value(0xffffff, "White")
}

