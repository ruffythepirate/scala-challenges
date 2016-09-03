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