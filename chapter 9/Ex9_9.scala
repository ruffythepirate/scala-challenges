// 9.9 Write a Scala program that counts how many files with .class extension are in a given directory and its subdirectories.

object Ex9_9 extends App {

  import java.io.File

var currentDir = if(args.size >= 1) new java.io.File(args(0)) else new java.io.File(".")

      val allDirs = subdirs(currentDir) :+ currentDir
      val numberOfDirs = allDirs.size
      println(s"Will be looking in $numberOfDirs dirs...")
      val numberOfClassFiles = allDirs.flatMap(getClassFiles(_)).size
      println(s"There are $numberOfClassFiles class files in the directory...")

  def subdirs(dir: File): Array[File] = {
    val children = dir.listFiles.filter(_.isDirectory)
    children ++: children.flatMap(subdirs _)
  }

  def getClassFiles(dir: File) = {
    dir.listFiles.filter(_.isFile).filter(isClassFile(_))
  }

  def isClassFile(dir: File) = {
    dir getName() endsWith(".class")
  }

}