package connectfour

const val DEFAULT_DIMENSIONS = "6 X 7"

const val PLAYER_1_SYMBOL = "o"
const val PLAYER_2_SYMBOL = "*"


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