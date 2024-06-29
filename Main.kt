package connectfour

const val DEFAULT_DIMENSIONS = "6 X 7"
const val DIMENSIONS_REGEX_PATTERN = "[0-9]+ X [0-9]+"
val dimensionsRegex = Regex(DIMENSIONS_REGEX_PATTERN)

fun main() {
    setUpGame()
}

fun setUpGame() {
    println("Connect Four")
    println("First player's name:")
    val firstPlayer: String = readln()
    println("Second player's name:")
    val secondPlayer: String = readln()
    var dimensions: String = getDimensionsFromUserOrDefault()
    while (!areDimensionsCorrect(dimensions)) {
        dimensions = getDimensionsFromUserOrDefault()
    }
    println("$firstPlayer VS $secondPlayer")
    println("$dimensions board")
    printBoard(dimensions)
}

fun getDimensionsFromUserOrDefault(): String {
    println("Set the board dimensions (Rows x Columns)")
    println("Press Enter for default (6 x 7)")
    val dimensions = readln().trim().uppercase()
        .replace("\\s+".toRegex(), "")
        .replace("X", " X ")
    return dimensions.ifEmpty {
        DEFAULT_DIMENSIONS
    }
}

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