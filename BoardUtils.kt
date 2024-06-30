package connectfour

const val DIMENSIONS_REGEX_PATTERN = "[0-9]+ X [0-9]+"
val dimensionsRegex = Regex(DIMENSIONS_REGEX_PATTERN)

const val NUMBER_OF_GAMES_REGEX_PATTERN = "[1-9][0-9]*"
val numberOfGamesRegex = Regex(NUMBER_OF_GAMES_REGEX_PATTERN)

const val COLUMN_REGEX_PATTERN = "[0-9]+"
val columnRegex = Regex(COLUMN_REGEX_PATTERN)

const val CHECK_WINDOW = 3

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

fun getNumberOfGamesOrDefault(): String {
    println("Do you want to play single or multiple games?")
    println("For a single game, input 1 or press Enter")
    println("Input a number of games:")
    val numberOfGames = readln()
    return numberOfGames.ifEmpty { "1" }
}

fun isNumberOfGamesCorrect(numberOfGames: String): Boolean {
    if (!numberOfGamesRegex.matches(numberOfGames)) {
        println("Invalid input")
        return false
    }
    return true
}


fun isColumnCorrect(column: String, game: Game): Boolean {
    if (column == "end") {
        return true
    }
    if (!columnRegex.matches(column)) {
        println("Incorrect column number")
        return false
    }
    val columnNumber = column.toInt() - 1  // deduct 1 because of 0 indexing
    if (columnNumber < 0 || columnNumber >= game.columns) {
        println("The column number is out of range (1 - ${game.columns})")
        return false
    }
    if (isColumnFull(columnNumber, game)) {
        println("Column ${columnNumber + 1} is full")
        return false
    }
    return true
}

private fun isColumnFull(columnNumber: Int, game: Game): Boolean {
    for (row in game.board) {
        if (row[columnNumber] == " ") {
            return false
        }
    }
    return true
}
