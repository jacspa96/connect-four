package connectfour

const val DIMENSIONS_REGEX_PATTERN = "[0-9]+ X [0-9]+"
val dimensionsRegex = Regex(DIMENSIONS_REGEX_PATTERN)

const val COLUMN_REGEX_PATTERN = "[0-9]+"
val columnRegex = Regex(COLUMN_REGEX_PATTERN)

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

fun printBoard(game: Game) {
    printBoardHeader(game.columns)
    for (i in 0 until game.rows) {
        for (j in 0 until game.columns) {
            print("║")
            print(game.board[i][j])
        }
        println("║")
    }
    printBoardFooter(game.columns)
}

private fun printBoardHeader(columns: Int) {
    print(" ")
    for (i in 1..columns) {
        print(i)
        print(" ")
    }
    println()
}

private fun printBoardFooter(columns: Int) {
    print("╚═")
    repeat(columns - 1) {
        print("╩═")
    }
    println("╝")
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