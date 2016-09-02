class Creature {
  def range: Int = 10
  val env: Array[Int] = new Array[Int](range)
}

class AntA extends Creature {
  override def range = 2
}

class AntB extends Creature {
  override val range = 5
}

object ex8_9 extends App {
//Def range is defined before primary constructor call =>
assert(new AntA().env.size == 2)

//Val range is defined after primary constructor call =>
assert(new AntB().env.size == 0)

println("Assertions are ok.")
}