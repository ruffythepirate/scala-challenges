/**
* 13.1 Write a function that, given a string, produces a map of the indexes of all characters. For example,
* indexes("mississippii") should return a map associating 'M' with the set (0), i with the set (1, 4, 7, 10) and so on.
* Use a mutable map of characters to mutable sets. How can you ensure that the set is sorted?
**/

def indexes(input: String) = {
  var indexes = collection.mutable.Map[Char, collection.mutable.Set[Int]]()

  for(i <- 0 until input.size) {
    if(! indexes.contains(input(i))) {
      indexes(input(i)) = collection.mutable.SortedSet[Int]()
    }
    indexes(input(i)) += i
  }
  indexes
}

// By using a SortedSet, we are guaranteed that it remains sorted.

/**
* 13.2
*
**/