//5.1 Improve counter class so that it doesn't turn negative at Int.MaxValue.
class Counter {
  private var value: Long = 0
  def increment() = {
    value += 1
  }
  def current() = value
}

//5.2 Write a class BackAccount with methods deposit and withdraw, and a read-only property balance

class BackAccount {
  private var _balance = 0

  def balance = _balance

  def deposit(value: Int) { _balance += value }
  def withdraw(value: Int) { _balance -= value }
}

//5.3 Write a class Time with read-only properties hours and minutes and a method before(other: Time): Boolean that checks whether this time comes before the other. A Time object should be constructred as new Time(hrs, min), where hrs is in military time format (between 0 and 23)

class Time(val hour: Int, val minutes: Int) {
  def before(other: Time): Boolean = {
    if (hour < other.hour || (hour == other.hour && minutes < other.minutes))
      true
    else false
  }
}

//5.4 Reimplement the Time class from the preceding excercise so that the internal representation is the number of minutes since midnight.
class Time(val hour: Int, val minutes: Int) {

  val minutesSinceMidnight = hour * 60 + minutes;
  def before(other: Time): Boolean = {
    minutesSinceMidnight < other.minutesSinceMidnight
  }
}

//5.5 Make a class Student with read-write JavaBeans properties name (of type String) and id (of type Long). What methods are generated? (Use avap to check.) Can you call the JavaBeans getters and setters in Scala. Should you?

import scala.beans.BeanProperty;

class Student(@BeanProperty val id: Long, @BeanProperty val name: String) {

}

//5.6 Create a class that turn negative ages into zero.

class Person(var age: Int) {
  age = if (age < 0) 0 else age;
}

//5.7 Make a class Person that puts in a name and seperates it into a firstname and a last name.

class Person(name: String) {
  val firstName = name.split(' ')(0)
  val lastName = name.split(' ')(1)
}

//the parameter should be without var or val, so that no field is created for the input field.

//5.8

class Car(val manufacturer: String, val name: String, val year: Int, var licensePlate: String) {
  def this(manufacturer: String, name: String) {
    this(manufacturer, name, -1, "");
  }
  def this(manufacturer: String, name: String, year: Int) {
    this(manufacturer, name, year, "");
  }
  def this(manufacturer: String, name: String, licensePlate: String) {
    this(manufacturer, name, -1, licensePlate);
  }
}

// 5.9 reimplement the previous example in C#.

public class Car {
	Car(string manufacturer, string name, int year = -1, string licensePlate = ""){
		Manufacturer = manufacturer;
		Name = name;
		Year = year;
		LicensePlate = licensePlate;
	}

	public string Manufacturer {get; private set;}
	public string Name {get; private set;}
	public int Year {get; private set;}
	public string LicensePlate {get;  set;}
}

// 5.10 Consider the class Employee, rewrite it to use explicit fields and a default primary constructor. Which form do you prefer. Why?

class Employee (val name : String, var salary: Double) {
	def this() {this("John Q. Public", 0.0) }
}

class Employee2 (nameF : String = "John Q. Public", salaryF : Double) {
  val name = nameF;
  var salary = salaryF && 0.0;
}
