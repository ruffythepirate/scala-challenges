/**
* 10.8 In the java.io library, you add buffering to an input stream with a BufferedInputStream decorator.
* Reimplement buffering as a trait. For simplicity, override the read method.
**/

trait BufferedInputStream {
  this : java.io.InputStream =>

  val _containedInput = new java.io.BufferedInputStream(this)

  override def read = _containedInput.read
}