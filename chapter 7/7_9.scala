  object Greeter extends App {
    import java.lang.System
    import java.util.Scanner

    println("Enter password: ")
    val sc = new Scanner(System.in)
    val password = sc.next
    if(password == "secret") {
      val username = System.getProperty("user.name")
      System.out.println(s"Greetings $username!") }
      else System.err.println("Error")
  }