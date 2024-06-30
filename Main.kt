package connectfour

const val DEFAULT_DIMENSIONS = "6 X 7"

const val PLAYER_1_SYMBOL = "o"
const val PLAYER_2_SYMBOL = "*"


fun main() {
    val game: Game = setUpGame()
    playGames(game)
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

    var numberOfGames: String
    do {
        numberOfGames = getNumberOfGamesOrDefault()
    } while (!isNumberOfGamesCorrect(numberOfGames))

    println("$firstPlayer VS $secondPlayer")
    println("$dimensions board")

    val (rows, columns) = dimensions.split(" X ").map { it.toInt() }
    val board = MutableList(rows) { MutableList(columns) { " " } }
    val game = Game(
        rows,
        columns,
        board,
        firstPlayer,
        secondPlayer,
        0,
        numberOfGames.toInt()
    )

    return game
}

fun playGames(game: Game) {
    if (game.numberOfGames == 1) {
        println("Single game")
        playGame(game)
        println("Game over!")
    } else {
        println("Total ${game.numberOfGames} games")
        for (gameCounter in 1..game.numberOfGames) {
            println("Game #$gameCounter")
            val firstPlayerStarts = gameCounter % 2 == 1
            playGame(game, firstPlayerStarts)
            if (game.isGameFinished) {
                break
            }
            game.printScore()
            game.resetGame()
        }
        println("Game over!")
    }
}

private fun playGame(game: Game, firstPlayerStarts: Boolean = true) {
    game.printBoard()
    var firstPlayerTurn = firstPlayerStarts
    while (true) {
        var column: String
        val currPlayer = if (firstPlayerTurn) game.firstPlayer else game.secondPlayer
        val currSymbol = if (firstPlayerTurn) PLAYER_1_SYMBOL else PLAYER_2_SYMBOL
        do {
            println("$currPlayer's turn:")
            column = readln()
        } while (!isColumnCorrect(column, game))
        if (column == "end") {
            game.isGameFinished = true
            break
        }
        val columnNumber = column.toInt() - 1  // deduct 1 because of 0 indexing
        for (i in game.rows - 1 downTo 0) {
            if (game.board[i][columnNumber] == " ") {
                game.board[i][columnNumber] = currSymbol
                game.filledCells++
                break
            }
        }
        game.printBoard()
        if (game.isGameWon(currSymbol)) {
            println("Player $currPlayer won")
            if (firstPlayerTurn) {
                game.scoreFirstPlayer += 2
            } else {
                game.scoreSecondPlayer += 2
            }
            break
        }
        if (game.filledCells == game.rows * game.columns) {
            println("It is a draw")
            game.scoreFirstPlayer++
            game.scoreSecondPlayer++
            break
        }
        firstPlayerTurn = !firstPlayerTurn
    }
}
