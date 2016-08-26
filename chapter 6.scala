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