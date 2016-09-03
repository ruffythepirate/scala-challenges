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

  class OrderedPoint(x : Int, y: Int) extends java.awt.Point(x, y) with scala.math.Ordered[java.awt.Point]{
    
    def compare(that: java.awt.Point) : Int = (if(x -that.x != 0) x-that.x else y - that.y)
    def ==(that: OrderedPoint) = x == that.x && y == that.y
  }

  val points = Array(OrderedPoint(5, 5), OrderedPoint(6,5), OrderedPoint(5,6))

  val sorted = points.sorted

  assert(sorted(0) == OrderedPoint(5,5))
  assert(sorted(1) == OrderedPoint(5,6))
  assert(sorted(2) == OrderedPoint(6,5))

}
