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

class Point(val x : Double, val y : Double) {
}

class LabeledPoint(val label: String, x:Double, y:Double) extends Point(x, y) {
}

// 8.6

