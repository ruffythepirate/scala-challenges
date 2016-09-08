/**
 10.1 The java.awt.Rectangle class has useful methods translate and grow that are unfortunately absent from classes such as java.awt.geom.Ellipse2D. 
 In Scala, you can fix this problem. Define a trait RectangleLike with concrete methods translate and grow. 
 Provide any abstract methods that you need for the implementation, so that you can mix in the trait like this: 
 
 val egg = new java.awt.geom.Ellipse2D.Double(5,10,20,30) with RectangleLike
 egg.translate(10, -10)
 egg.grow(10, 20)
**/

object Ex10_1 extends App {

  trait RectangleLike {
    this: java.awt.geom.RectangularShape => 
    def translate(dx : Double, dy: Double) {
      this.setFrame(getX + dx, getY + dy, getWidth, getHeight)      
    }

    def grow(dw: Double, dh: Double) {
      this.setFrame(getX, getY, getWidth + dw, getHeight + dh)
    }
  }

 val egg = new java.awt.geom.Ellipse2D.Double(5,10,20,30) with RectangleLike
 egg.translate(10, -10)
 egg.grow(10, 20)
 assert(egg.getX == 15)
 assert(egg.getY == 0)
 assert(egg.getWidth == 30)
 assert(egg.getHeight == 50)
} 

/**
* 10.2 Define a class OrderedPoint by mixing scala.math.Ordered[Point] into java.awt.Point.
* User lexicographic ordering, i.e. (x,y) < (x', y') if x < x' or x = x' and y < y'.
**/

object Ex10_2 extends App {
  
  object OrderedPoint {
    def apply (x: Int, y: Int) = {
      new OrderedPoint(x, y)
    }
  }

  class OrderedPoint(x : Int, y: Int) extends java.awt.Point(x, y) with scala.math.Ordered[OrderedPoint]{
    
    def compare(that: OrderedPoint) : Int = (if(x -that.x != 0) x-that.x else y - that.y)
    def ==(that: OrderedPoint) = x == that.x && y == that.y
  }

  val points = Array(OrderedPoint(5, 5), OrderedPoint(6,5), OrderedPoint(5,6))

  val sorted = points.sorted

  assert(sorted(0) == OrderedPoint(5,5))
  assert(sorted(1) == OrderedPoint(5,6))
  assert(sorted(2) == OrderedPoint(6,5))
}



/**
* 10.3 Look at the BitSet class, and make a diagram of all its superclasses and traits. Ignore the type parameters (everything inside the [...]).
* Then give the linearization of the traits.
**/

/**
*  trait BitSet extends SortedSet[Int] with BitSetLike[BitSet]
*  trait SortedSet[A] extends Set[A] with SortedSetLike[A, SortedSet[A]]
*  trait Set[A] extends (A) ⇒ Boolean with Iterable[A] with GenSet[A] with GenericSetTemplate[A, Set] with SetLike[A, Set[A]]
*  Iterable[+A] extends Traversable[A] with GenIterable[A] with GenericTraversableTemplate[A, Iterable] with IterableLike[A, Iterable[A]]

*  trait BitSetLike[+This <: BitSetLike[This] with SortedSet[Int]] extends SortedSetLike[Int, This]
*  trait SortedSet[A] extends Set[A] with SortedSetLike[A, SortedSet[A]]
*  trait SortedSetLike[A, +This <: SortedSet[A] with SortedSetLike[A, This]] extends Sorted[A, This] with SetLike[A, This]

*  Serializable, java.io.Serializable, collection.BitSet, BitSetLike[BitSet], SortedSet[Int], collection.SortedSet[Int], SortedSetLike[Int, BitSet], Sorted[Int, BitSet], Set[Int], Iterable[Int], Traversable[Int], Immutable, AbstractSet[Int], collection.Set[Int], SetLike[Int, BitSet], Subtractable[Int, BitSet], GenSet[Int], GenericSetTemplate[Int, Set], GenSetLike[Int, BitSet], (Int) ⇒ Boolean, AbstractIterable[Int], collection.Iterable[Int], IterableLike[Int, BitSet], Equals, GenIterable[Int], GenIterableLike[Int, BitSet], AbstractTraversable[Int], collection.Traversable[Int], GenTraversable[Int], GenericTraversableTemplate[Int, Set], TraversableLike[Int, BitSet], GenTraversableLike[Int, BitSet], Parallelizable[Int, ParSet[Int]], TraversableOnce[Int], GenTraversableOnce[Int], FilterMonadic[Int, BitSet], HasNewBuilder[Int, BitSet], AnyRef, Any
**/

/**
* 10.4 Provide a CryptoLogger trait that encrypts the log messages with the Caesar cipher.
* The key should be 3 by default, but it should be overridable by the user. 
* Provide usage examples with the default key and a key of -3
**/

object Ex10_4 extends App {

  trait ConsoleLogger {
    def log(message: String) { println(message)}
  }


  trait CryptoLogger extends ConsoleLogger {
    override def log (message: String) {
      val ciphered = cipherMessage(message)
      super.log(ciphered)
    }
    val alphabet = 'a' to 'z'

    private var offset = 3
    def setOoffset(newValue: Int) = { offset = newValue; while(offset < 0) offset += alphabet.size }

    def transformChar(c : Char) = {
      val newChar = c match {
            case c if alphabet.contains(c.toLower) => alphabet((alphabet.indexOf(c.toLower) + offset) % alphabet.size)
            case c => c 
          }
          if(c.isUpper) newChar.toUpper else newChar
    }

    def cipherMessage(message: String) = {
        for(c <- message) yield transformChar(c)
    }
  }

  class Foo extends CryptoLogger {
    def bar {
      log("Foo Bar!")
    }
  }

  val myClass1 = new Foo(){setOffset_(-3)}
  val myClass2 = new Foo()

  myClass1.bar
  myClass2.bar
}

/**
* 10.5 The JavaBeans specification has the notion of a property change listener, a standardized way for beans to communicate changes in their properties.
* The PropertyChangeSupport class is provided as a convenience superclass for any bean that wishes to supprt property change listeners. 
* Unfortunately, a class that already has another superclass- such as JComponent- must reimplement the methods. 
* Reimplement PropertyChangeSupport as a trait, and mix it into the java.awt.Point class.
**/



/**
* 10.6 In the Java AWT library, we have a class Container, a subclass of Component that collects multiple components.
* For example, a Button is a Component, but a Panel is a Container. 
* That's the composite pattern at work.
* Swing has JComponent and JButton, but if you look closely, you will notice something strange.
* JComponent extends Container, even though it makes no sense to add other components to, say, a JButton.
* The Swing designers would have ideally preffered the design in Figure 10-4.
* Component  <------| 
*     ^         Container
* JComponent <------^
*     ^         JContainer
* JButton           ^
*                 JPanel
*
* But that's not possible in Java. Explain why not. How could the design be executed in Scala with traits?
**/

// It is not possible because Container and JComponent are both classes. But it is done because
// they probably want implementation logic from both in for example the JPanel.

trait MyJComponent extends javax.swing.JComponent {

}

trait MyContainer extends 

/**
* 10.7 There are dozens of Scala trait tutorials with silly examples of barking dogs or philosophizing frogs.
* Reading through contrived hierarchies can be tedious and not very helpful, but designing your own is very illuminating.
* Make your own silly trait hierarchy example that demonstrates layered traits, concrete and abstract methods, and concrete and abstract fields.
**/

/**
* 10.8 In the java.io library, you add buffering to an input stream with a BufferedInputStream decorator.
* Reimplement buffering as a trait. For simplicity, override the read method.
**/

trait BufferedInputStream {
  this : java.io.InputStream =>

  val _containedInput = new java.io.BufferedInputStream(this)

  override def read = _containedInput.read
}

/**
* 10.9 Using the logger traits from this chapter, add logging to the solution of the preceding problem that demonstrates buffering
**/

/**
* 10.10 Implement a class IterableInputStream that extends java.io.InputStream with the trait Iterable[Byte].
**/ 

class IterableInputStream extends java.io.InputStream with Iterable[Byte] {

}


