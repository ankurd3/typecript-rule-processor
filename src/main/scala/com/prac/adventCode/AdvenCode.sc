1+1

//Day1
val path = "/Users/ankurd3/git/rule-processor/src/main/resources/day1.txt"
scala.io.Source.fromFile(path).getLines.foldLeft(0){(a,b) => a.toInt + ((b.toInt / 3) - 2)}