/**
* 12.1 Write a function values(fun: (Int) => Int, low:Int, high: Int) that yields a collection of functiona inputs and outpus in a given range. 
* For example, values( x => x * X, -5, 5) should produce a collection of pairs (-5, 25), (-4, 16), (-3, 9),..., (5, 25).
**/

def values(fun: (Int) => Int, low: Int, high: Int) = {
  for(x <- low to high) yield x -> fun(x)
}

/**
* 12.2 How do you get the largest element of an array with reduceLeft?
**/

(1 to 15) reduceLeft(math.max)

/**
* 12.3 Implement the factorial function using to an reduceLeft, without a loop or recursion
**/

(1 to 5) reduceLeft((x, y) => if(x > 0) x*y else 1)

/**
* 12.4 The previous implementation needed a special case when n < 1. 
* Show how you can avoid this with foldLeft. (Look at the Scaladoc for foldLeft. It's like reduceLeft except that the first value in the chain of combined values is supplied in the call.)
**/

(-1 to 5) foldLeft(1) ((x, y) => if(y > 0) x*y else x)

/**
* 12.5 Write a function largest( fun: (Int) => Int, inputs Seq[Int]) that yields the largest value of a function within a given sequence of inputs. 
* For example, largest(x => 10*x - x*x, 1 to 10) should return 25.
* Don't use a loop or recursion
**/

def largest(fun : (Int) => Int, inputs: Seq[Int]) = {
  inputs.foldLeft(fun(inputs(0))) ((x, y) => math.max(x, fun(y)))
}

/**
* 12.6 Modify the previous function to return the input at which the output is largest. 
* For example, largestAt(x => 10*x - x*x, 1 to 10) should return 5
**/

def largest(fun : (Int) => Int, inputs: Seq[Int]) = {
  inputs.reduceLeft((x, y) => if(fun (x) > fun(y)) x else y)
}

/**
* 12.7 It's easy to get a sequence of pairs, for example
*     val pairs = (1 to 10) zip (11 to 20)
* Now suppose you want to do something with such a sequence - say, add up the values. But you can't do pairs.map(_ + _)
* The function _ + _ takes two Int parameters, not an (Int, Int) Pair. Write a function adjust toPair that receivs a function of type (Int, Int) => Int
* and returns the equivaluent function that operates on a pair, for example, adjustToPair(_ * _)((6,7)) is 42
**/

def adjustToPair(fun : (Int, Int) => Int) : ((Int, Int)) => Int = {
  x: (Int, Int) => fun(x._1, x._2)
}

/**
* 12.8 In section 12.8, "Currying" on page 149, you saw the corresponds method used with two arrays of strings. 
* Make a call to corresponds that checks whether the elemtns in an array of strings have the lengths given in an array of integers.
**/

Array("Black", "Blue", "Box").corresponds(Array(5,4,3))(_.length == _)

/**
* 12.9 Implement corresponds without currying. Then try the call from the preceeding exercise.
* What problem do you encounter?
**/


def arrayEquals[A, B](first: Array[A], second: Array[B], fun: (A, B) => Boolean) = {
  if(first.size == second.size) {
    (for(i <- 0 until first.size) yield fun(first(i), second(i))).reduceLeft( _ && _)
  } 
  else false
}

arrayEquals(Array("Black", "Blue", "Box"), Array(5,4,3), (x : String, y: Int) => x.length == y)

// The type cannot be inferred from my method call. Why not? It might be because in the first case, the types are determined before the 
// compiler needs to work out what the types are for the method.

/**
* 12.10 Implement an unless control abstraction that works just like if, but with an inversted condition. 
* Does the first parameter need tobe a call-by-name parameter? 
* Do you need currying?
**/

def unless(condition: => Boolean)(block: => Unit) {
  if(! condition) {
    block
  }
}

// The currying is to get the neet syntax so that it really looks like an if statement. Otherwise one could ofcourse send in a lambda method
// as an argument.
