// 3.1 Write a code snippet that creates an array of size n with Random numbers between [0, n)

def randomArray(n : Int) : Array[Int] {val a = for(i <- 0 to n) yield Random.nextInt(n); a.toArray}

// 3.2 make an array that swaps elements

def swapArray(a : Array[Int]) : Array[Int] = a.grouped(2).flatMap(_.reverse).toArray

// 3.3 Make the array swap but with yield

def swapArray(a : Array[Int]) : Array[Int] {val a = for(i <- 0 to a.length) yield if(i % 2 == 0 && i >= a.length) a(i) else if(i%2 == 0) a(i+1) else a(i-1)}

// 3.4 Take an array and join all positive numbers in their order of appearance with all the zero or negative in their order of appearance.

a.filter( _ > 0) ++ a.filter(_ <= 0)

//3.5 How do you computer the average of Array[Double]

val b = Array(1.3, 1.4, 1.5);
b.sum / b.length;

//3.6 How do you rearrange the elements of an Array[Int] so that they are in reversed sorted order

b.sorted.reverse

//3.6b How do you do the same with an ArrayBuffer?

c.sorted.reverse

//3.7 How do you remove duplicates?

a.distinct

// 3.8 Rewrite the example at the end of Section 3.4 Transforming Arrays on page 32. Collect indexes of the negative elements, reverse the sequence, drop the last index and call a.remove(i) for each index. Compare the efficiency of this approach with the two approaches in Section 3.4

var first = true
var n = a.length
var i = 0
while(i < n) {
	if(a(i) >= 0) i+= 1 else {
	if(first){ first = false; i+=1}
	else {a.remove(i); n-=1
	}
	}
}

3028ms

var first = true
val indexes = for (i <- 0 until a.length if first || a(i) >= 0) 
	yield {
		if(a(i) < 0) first = false; i
	}
for(j<- 0 until indexes.length) a(j) = a(indexes(j))
a.trimEnd(a.length - indexes.length)

600ms

val indexes = for(i <- 0 until a.length if a(i) < 0) yield i
indexes = indexes.reverse; for(j <- 0 until indexes.length-1) a.remove(indexes(j))

// 3.9 Make a collection of all time zones returned by java.util.TimeZone.getAvailableIDs that are in America. Strip off the "America/" prefix and sort the result

{for(s <- java.util.TimeZone.getAvailableIDs if s.startsWith("America/")) yield s.stripPrefix("America/")}.sorted

// 3.10 Import java.awt.datatransfer._ and make an object of type SystemFlavorMap with the call
// val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
// Then call the getNativesForFlavor method with parameter DataFlavor.imageFlavor and get the return value as a Scala buffer. (Why this obscure class?)




def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block    // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) + "ns")
    result
}

// Now wrap your method calls, for example change this...
val result = 1 to 1000 sum

// ... into this
val result = time { 1 to 1000 sum }

