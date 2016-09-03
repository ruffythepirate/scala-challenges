/**
* 11.5 Provide operators that construct an HTML table. For example,

Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, .NET"

should produce

<table><tr><td>Java</td><td>Scala</td></tr><tr><td>Gosling...
**/

object Table {
  def apply() = new Table(Array(Array[String]()))
}

class Table (content: Array[Array[String]]) {

  def |(column: String) : Table = {
    val clone = content.clone
    clone(content.size - 1) = clone(content.size - 1) :+ column
    new Table(clone)
  }

  def ||(row: String): Table = {
    new Table(content :+ Array(row) )
  }

  override def toString = {
    val stringBuild = new StringBuilder()
    stringBuild.append("<table>")
    for(row <- content) {
      stringBuild.append("<tr>")
      for(column <- row) {
        stringBuild.append("<td>")
        stringBuild.append(column)
        stringBuild.append("</td>")
      }
      stringBuild.append("</tr>")
    }
      stringBuild.append("</table>")
      stringBuild.toString
  }
}