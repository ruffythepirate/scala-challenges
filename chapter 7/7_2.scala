package com.scala.ch07 {
  object Main {
    val a = 1
  }

  package com.scala.ch07 {
    object Main extends App {
      val a = 1

      assert( _root_.com.scala.ch07.Main.a == com.scala.ch07.Main.a)
    }
  }
}