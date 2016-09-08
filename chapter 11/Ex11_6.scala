/**
* 11.6 Provide a class ASCIIArt whose objects contain figures such as

  /\_/\
  (' ')
  ( - )
  | | |
  (_|_)

Supply operators for combining two ASCIIArt figures horizontally

or vertically. Choose operators with appropriate precendence.

Operator precedence:
  * / %
  + -
  :
  < >
  ! = 
  &
  ^
  |
  A character that is not an operator character
  Lowest precedence: Assignment operators
  **/

object Ex11_6 extends App {

  class AsciiArt (val content : Array[String]) {

    // Vertical addition
    def +(other : AsciiArt) = {
      new AsciiArt(content ++ other.content)
    }

    // Horizontal addition
    def *(other : AsciiArt) = {
      val verticalLength = math.max(content.size, other.content.size)
      val longestLength = content.map(_.size).max
      val newContent = extendContent(verticalLength).zip(other.extendContent(verticalLength))
          .map(t => t._1.padTo(longestLength + 1, ' ') + t._2)
      new AsciiArt(newContent)
    }

    private def extendContent(toLength: Int) = {
      val emptyStringArray = (1 to (toLength - content.size)).map(t => "").toArray
      content ++ emptyStringArray
    }

    override def toString = {
      content.mkString("\n")
    }
  }
}