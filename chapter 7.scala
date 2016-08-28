//7.1 Write an example program to demonstrate that package com.horstmann.impatient is not the same as package com package horstmann package impatient.

package com {
  object myObject {
    def hello = println("Hello")
  }
}

package com.horstmann.impatient {
    object firstObject {
   // myObject.hello()        //Doesn't work, com is not in scope.
   }
}

package com {
  package horstmann {
    package impatient {
      object otherObject {
        myObject.hello      //works, com is in scope.
      }
    }
  }
}

//7.2 Write a puzzler that baffles your Scala friends, using a package com that isn’t at the top level.


package com.scala.ch07 {
  object Ex02 {
    val a = 1
  }
  package com.scala.ch07 {
    object Ex02 extends App {
      val a = 2

      assert( _root_.com.basile.scala.ch07.Ex02.a == com.basile.scala.ch07.Ex02.a)
    }
  }
}

/** 
  7.3 Write a package random with functions nextInt(): Int, nextDouble(): Double, and setSeed(seed: Int): Unit. 
  To generate random numbers, use the linear congruential generator

  next = (previous x a + b) mod 2**n,

  where a = 1664525, b = 1013904223, n = 32, and the initial value of previous is seed.
**/

object random {

  var previous = 1 : BigInt
  val a = 1664525 : BigInt; val b = 1013904223 : BigInt

  def nextInt (): Int = {
    previous = (previous * a + b) % (BigInt(1) << 31)

    previous.toInt
  }

  def nextDouble(): Double = {
    nextInt
  }

  def setSeed(seed: Int) {
    previous = seed
  }

}

/**
  7.4 Why do you think the Scala language designers provided the package object syntax instead of simply letting you add functions and variables to a package?
**/

// Because a package could be huge. Creating objects makes you gather variables and methods that logically belong together into smaller units.

/**
  7.5 What is the meaning of private[com] def giveRaise(rate: Double)? Is it useful?
**/

// It makes the method private to the package com. Yes, it is useful to have private methods that cannot be accessed from the outside.
// In pure Functional thinking one should minimize state though, so the number of these methods that are needed should be small.

/**
  7.6 Write a program that copies all elements from a Java hash map into a Scala hash map. Use imports to rename both classes.
**/ 
{
  import java.util.{HashMap => JavaHashMap}
  import scala.collection.mutable.{HashMap => ScalaHashMap}
  import scala.collection.JavaConversions.asScalaSet

  val javaMap = new JavaHashMap[String, String]()
  javaMap.put("hi", "ho")
  javaMap.put("ho", "ho")
  javaMap.put("hoho", "ho")

  val scalaMap = new ScalaHashMap[String, String]()

  for(k <- javaMap.keySet) {
    scalaMap(k) = javaMap.get(k)
  }

  println(scalaMap)
}

// 7.7 From previous exercise, move all imports to inne most possible scope -> already done.

// 7.8 What is the effect of import java._, import javax._

import java._
new util.HashMap[String, String]()

//All java namespaces are imported. This will override possible collissions with scala namespaces, which is typically a bad idea.

/** 
  7.9 Write a program that imports the java.lang.System class, reads the user name from the user.name system proeprty, reads a password from the Console obejct, and prints a message to the standard error stream if the password is not "secret". Otherwise, print a greeting to the standard output stream.
  Do not use any other imports, and do not use any qualified names (with dots)
**/

object Greeter extends App {
  import java.lang.System
  import java.util.Scanner

  println("Enter password: ")
  val sc = new Scanner(System.in)
  val password = sc.next
  if (password == "secret") {
    val username = System.getProperty("user.name")
    System.out.println(s"Greetings $username!")
  } else System.err.println("Error")
}

// 7.10 Apart from StringBuilder, what other members of java.lang does scala package override?

// The basic types, Boolean etc. Process, 
