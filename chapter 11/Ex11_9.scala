
// 11.9 Define an unapply operation for the RichFile class that extracts the file path, name, and extension.
// For example, the file /home/cay/readme.txt has path /home/cay, name readme and extension txt.

object Ex11_9 extends App {

  object RichFileExtractor {

    def unapply(file : java.io.File) : Option[(String, String, String)] = {

      val absolutePath = file.getAbsolutePath

      val pathTokens = absolutePath.split(java.io.File.separator)

      val fileNameWithExtension = pathTokens.last

      val fileName = 
        if(fileNameWithExtension.contains('.')) fileNameWithExtension.split('.').dropRight(1).mkString(".") else fileNameWithExtension

      val extension = 
        if(fileNameWithExtension.contains('.')) fileNameWithExtension.split('.').last else ""

      Some((pathTokens.dropRight(1).mkString(java.io.File.separator), fileName, extension.toString))
    }
  }  
}

