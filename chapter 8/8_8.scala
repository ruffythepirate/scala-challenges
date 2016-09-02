class Person(val name: String) {
  override def toString = getClass.getName + s"[name=$name]"
}

class SecretAgent(codename : String) extends Person(codename) {
  override val name = "secret"

  override val toString = "secret"
}