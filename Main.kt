package connectfour

const val DEFAULT_DIMENSIONS = "6 X 7"

const val PLAYER_1_SYMBOL = "o"
const val PLAYER_2_SYMBOL = "*"


fun main() {
    val game: Game = setUpGame()
    playGame(game)
}

fun setUpGame(): Game {
    println("Connect Four")
    println("First player's name:")
    val firstPlayer: String = readln()
    println("Second player's name:")
    val secondPlayer: String = readln()
    var dimensions: String
    do {
        dimensions = getDimensionsFromUserOrDefault()
    } while (!areDimensionsCorrect(dimensions))

    println("$firstPlayer VS $secondPlayer")
    println("$dimensions board")

    val (rows, columns) = dimensions.split(" X ").map { it.toInt() }
    val board = MutableList(rows) { MutableList(columns) {" "} }
    val game = Game(rows, columns, board, firstPlayer, secondPlayer)

    return game
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

fun playGame(game: Game) {
    printBoard(game)
    var firstPlayerTurn = true;
    while (true) {
        var column: String
        val currPlayer = if (firstPlayerTurn) game.firstPlayer else game.secondPlayer
        val currSymbol = if (firstPlayerTurn) PLAYER_1_SYMBOL else PLAYER_2_SYMBOL
        do {
            println("$currPlayer's turn:")
            column = readln()
        } while (!isColumnCorrect(column, game))
        if (column == "end") {
            println("Game over!")
            return
        }
        val columnNumber = column.toInt() - 1  // deduct 1 because of 0 indexing
        for (i in game.rows - 1 downTo 0) {
            if (game.board[i][columnNumber] == " ") {
               game.board[i][columnNumber] = currSymbol
               break
            }
        }
        printBoard(game)
        firstPlayerTurn = !firstPlayerTurn
    }
}