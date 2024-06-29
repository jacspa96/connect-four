package connectfour

const val DIMENSIONS_REGEX_PATTERN = "[0-9]+ X [0-9]+"
val dimensionsRegex = Regex(DIMENSIONS_REGEX_PATTERN)

fun areDimensionsCorrect(dimensions: String): Boolean {
    if (!dimensionsRegex.matches(dimensions)) {
        println("Invalid input")
        return false
    }
    val (row, columns) = dimensions.split(" X ").map { it -> it.toInt() }
    if (row < 5 || row > 9) {
        println("Board rows should be from 5 to 9")
        return false
    }
    if (columns < 5 || columns > 9) {
        println("Board columns should be from 5 to 9")
        return false
    }
    return true
}

fun printBoard(dimensions: String) {
    val (row, columns) = dimensions.split(" X ").map { it -> it.toInt() }
    print(" ")
    for (i in 1..columns) {
        print(i)
        print(" ")
    }
    println()
    repeat(row) {
        repeat(columns) {
            print("║ ")
        }
        println("║")
    }
    print("╚═")
    repeat(columns - 1) {
        print("╩═")
    }
    println("╝")
}