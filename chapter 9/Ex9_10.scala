// 9.10 Expand the example with the seralizable Person class that stores a collection of friends. Construct a few Person objects, make som of the friends of naother, and then save an Array[Person] to a file. Read the array back in and verify that the friend relations are intact.

object Ex9_10 extends App {

  val filename = "person.txt"

  def serializePersonsToFile( persons : Array[Person], filename : String) {
    val out = new java.io.ObjectOutputStream(new java.io.FileOutputStream(filename))
    out.writeObject(persons)
    out close
  }

  def deserializePerson(filename: String) = {
    val in = new java.io.ObjectInputStream(new java.io.FileInputStream(filename))
    val people = in.readObject().asInstanceOf[Array[Person]]
    in.close()
    people
  }

  @SerialVersionUID(1L) case class Person(val name: String) extends Serializable {
    private val friends = new collection.mutable.ArrayBuffer[Person]()

    def addFriend(friend: Person) {
      friends :+ friend
    }
  }
}