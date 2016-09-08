/**
*
*  11.8 Provide a class Matrix - you can choose whether you want to implement 2 x 2 matrices of any size, square matrices of any size, or m x n matrices.
*       Supply operations + and *.
*       The latter should also work with scalars, for example mat*2. A single element should be accessible as mat(row, col)
**/

object Ex11_8 extends App {

  object Matrix {

    def apply(rows: Int, cols: Int) = new Matrix(Array.ofDim[Int](rows,cols))
  }

  class Matrix (private val _content: Array[Array[Int]]) {

    private val _rows = _content.size
    private val _columns = if(_rows > 0) _content(0).size else 0
    
    def apply(row : Int, col: Int) = _content(row)(col)

    def update(row : Int, col: Int, value: Int) {_content(row)(col) = value}

    def *(other: Matrix) = {
      val otherRows = other._rows; val otherColumns = other._columns
      val rows = _rows; val columns = _columns
      if(_columns != other._rows) {
        throw new Exception(s"Invalid dimensions of matrices: [$otherRows, $otherColumns] cannot be multiplied to a matrix with dim [$rows, $columns]")
      }

      val newContent = Array.ofDim[Int](_rows, otherColumns)

      for(i <- 0 until rows) {
        for(j <- 0 until otherColumns) {
          for( k <- 0 until columns) {
            newContent(i)(j) += this(i, k) * other(k, j);
          }
        }
      }
      new Matrix(newContent)
    }

    def *(scalar : Int) = {
      new Matrix(_content.map(_.map(_*scalar)))
    }

    def +(other :Matrix) = {
      val otherRows = other._rows; val otherColumns = other._columns
      val rows = _rows; val columns = _columns

      if(_rows != other._rows || _columns != other._columns) {
        throw new Exception(s"Invalid dimensions of matrices: dim [$otherRows, $otherColumns] cannot be added to a matrix with dim [$rows, $columns]")
      }

      val newContent = Array.ofDim[Int](_rows, _columns)
      for(i <- 0 until _rows) {
        for(j <- 0 until _columns) {
          newContent(i)(j) = this(i, j) + other(i, j);
        }
      }

      new Matrix(newContent)
    }

    override def toString = "[" + _content.map(_.mkString(", ")).mkString("\n") + "]"

  }

}