// 8.1 Extend the following BankAccount class to a ChkingAccount class that charges $1 for every deposit and withdrawal.

class BankAccount(initialBalance: Double) {
  private var balance = initialBalance
  def currentBalance = balance

  def deposit(amount : Double) = {balance += amount; balance}
  def withdraw(amount : Double) = {balance -= amount; balance}

  override def toString = balance.toString
}

class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance){

  override def deposit(amount : Double) = super.deposit(amount - 1)
  override def withdraw(amount : Double) = super.withdraw(amount - 1)
}

/***
8.2 Extend the BackAccount class of the preceding exercise into a class SavingsAccount that earns interest every month (when a method earnMonthlyInterest is called) and has three free deposits or withdrawals every month. Reset the transaction count in the earn MonthlyInterest method.
***/

class SavingsAccount(initialBalance: Double, val interestRate: Double) extends BankAccount(initialBalance){
  private var monthDeposits = 0
  private var monthWithdraws = 0

  private val freeActions = 3

  def earnMonthlyInterest = {
    monthDeposits = 0; monthWithdraws = 0;
    super.deposit( interestRate * currentBalance)
    currentBalance
  }

  override def deposit(amount : Double) = {
    if(monthDeposits < freeActions) {monthDeposits += 1; super.deposit(amount)} 
    else super.deposit(amount -1)
  }

  override def withdraw(amount : Double) = {
    if(monthWithdraws < freeActions) {monthWithdraws += 1; super.deposit(amount)} 
    else super.deposit(amount -1)
  }
}

/**
8.3 Consult your favorite Java or C++ textbook that is sure to have an example of a toy inheritance hierarchy, perhaps involving employees
, pets, graphical shapes, or the like.
Implement the example in Scala.
**/

class Bicycle (startCadence : Int, startGear : Int, startSpeed : Int){
        
    // the Bicycle class has three fields
    private var cadence = startCadence;
    private var gear = startGear
    private var speed = startSpeed
                
    def setCadence(newValue : Int) = cadence = newValue
        
    def setGear(newValue : Int) = gear = newValue
        
    def applyBrake(decrement: Int) = speed -= decrement
        
    def speedUp(increment: Int) = speed += increment
}

class MountainBike (startCadence : Int, startGear : Int, startSpeed : Int, startHeight: Int) extends Bicycle(startCadence, startGear, startSpeed){
    private var height = startHeight
        
    def setHeight(newValue: Int) = height = newValue
}

/**
 8.4 Define an abstract class Item with methods price and description. A SimpleItem is an item whose price and description are specified int the constructor.
 Take advantage of the fact that a val can override a def. A Bundle is an item that contains other items.
 Its price is the sum of the prices in the bundle. Also provide a mechanism for adding items to the bundle and a suitable description method.
 **/

abstract class Item {
  def price : Double
  def description : String
}

class SimpleItem(override val price : Double, override val description : String) extends Item{
}

class Bundle extends Item {
  private val items = new collection.mutable.ArrayBuffer[Item]

  def addItem(item : Item) = items.append(item)

  override def price = items.map(_.price).sum

  override def description = {
    val sb  = new StringBuilder()
    sb.append("Bundle contains of items:\n")
    items.foreach(item => sb.append(item.description))
    sb.toString
  }
}

/**
  8.5 Design a class Point whose x and y coordinate values can be provided in a constructor.
  Provide a subclass LabeledPoint whose constructor takes a label value and x and y coordinate such as new LabeledPoint("Black Thursday", 23, 23)
**/

case class Point(val x : Double, val y : Double) {
}

class LabeledPoint(val label: String, x:Double, y:Double) extends Point(x, y) {
}

// 8.6 Define an abstract class Shape with an abstract method centerPoint and subclasses Rectangle and Circle. Provide appropriate constructors for the subclasses and override the centerPoint method in each subclass.

abstract class Shape(val topLeft: Point) {
  def centerPoint : Point
}

class Circle(radius: Double, x: Double, y: Double) extends Shape(new Point(x, y)) {
  override val centerPoint = new Point(x + radius, y + radius)
}

class Rectangle(w: Double, h:Double,  x: Double, y: Double) extends Shape(new Point(x, y)) {
  override val centerPoint = new Point(x + w/2, y + h/2)
}

// 8.7 Provide a class Square that extends java.awt.Rectangle and has three constructors: one that constructs a square with a give corner (0, 0) and a given width, and one that constructs a square with corner (0, 0) amd width 0

class Square(topLeft : java.awt.Point, width: Int) extends java.awt.Rectangle(topLeft.x, topLeft.y, width, width) {
  def this() {
    this(new java.awt.Point(0, 0), 0)
  }

  def this(width: Int) {
    this(new java.awt.Point(0, 0), width)
  }
}

// 8.8 Compile the Person and SecretAgent classes in Section 8.6, "Overriding Fields", on page 89 and analyze the class files with javap. How many name fields are there? How many name getter methods are there? What do they get? (Hint: Use the -c and -private options.)

class Person(val name: String) {
  override def toString = getClass.getName + s"[name=$name]"
}

class SecretAgent(codename : String) extends Person(codename) {
  override val name = "secret"

  override val toString = "secret"
}

//It seems like there is only one name() method in each of the classes, one returning a constant string and one returning the property name.

// 8.9 In the Creature class of section 10, "Construction Order and Early Definitions," on page 92, replace val range with a def. What happens when you also use a def in the Ant subclass? What happens when you use a vaö in the subclass? Why?

class Creature {
  val range: Int = 10
  val env: Array[Int] = new Array[Int](range)
}

  class AntA extends Creature {
    override def range = 2
  }

  class AntB extends Creature {
    override val range = 5
  }

  //Def range is defined before primary constructor call =>
  assert( new AntA().env.size == 2)

  //Val range is defined after primary constructor call =>
  assert( new AntB().env.size == 0)

  /**
  * 8.10 The file scala/collection/immutable/Stack.scala contains the defintions
  * class Stack[A] protected (protected val elems: List[A])
  * Explain the meanings of the protected keywords.
  * (Hint: Review the discussion of private constructors in Chapter 5)
  **/

  // The first protected indicates that only inherited classes and auxilliary constructors can call the primary constructor.
  // The second protected indicates that the elements field will be protected.


