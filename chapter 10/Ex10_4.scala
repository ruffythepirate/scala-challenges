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