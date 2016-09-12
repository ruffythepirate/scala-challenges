// 11.10 Define an unapplySeq operation for the RichFile class that extracts all path segments. 
// For example, for the file /home/cay/readme.txt, you should produce a sequence of three segments: home, cay, and readme.txt.

object Ex11_10 extends App {

  object RichFileExtractor {

    def unapplySeq(file: java.io.File) : Option[Seq[String]] = {
      Some(file.getAbsolutePath.split(java.io.File.separator))

    }

  }

}