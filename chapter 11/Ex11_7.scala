/**
  11.7 Implement a class BitSequence that stores a sequence of 64 bits packed in a Long value. Supply apply and update operators to get and set anindividual bit.
**/

object Ex11_7 extends App {

  object BitSequence {
    def apply(firstBit : Int) = {
      val initalValue = 0l | 1 << firstBit
      
      new BitSequence (initalValue)
    }

    def apply() = {
      new BitSequence (0l)
    }
  }

  class BitSequence (private var _value : Long) {

    def value = _value

    def update(index : Int, newValue : Boolean) {

      _value = if(newValue) (_value | (1 << index)) else (_value ^ (1 << index))
    }
  }

  assert(BitSequence(0).value == 1)

  val myBitSequence = BitSequence(0)
  myBitSequence(1) = true

  assert(myBitSequence.value == 3)
}