package connectfour

class Game(
    val rows: Int,
    val columns: Int,
    var board: MutableList<MutableList<String>>,
    val firstPlayer: String,
    val secondPlayer: String,
    var filledCells: Int,
    val numberOfGames: Int,
    var scoreFirstPlayer: Int = 0,
    var scoreSecondPlayer: Int = 0,
    var isGameFinished: Boolean = false
) {
    fun printBoard() {
        printBoardHeader(this.columns)
        for (i in 0 until this.rows) {
            for (j in 0 until this.columns) {
                print("║")
                print(this.board[i][j])
            }
            println("║")
        }
        printBoardFooter(this.columns)
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

    fun isGameWon(symbol: String): Boolean {
        return isGameWonHorizontally(symbol)
                || isGameWonVertically(symbol)
                || isGameWonDiagonally(symbol)
    }

    private fun isGameWonHorizontally(symbol: String): Boolean {
        for (row in this.board) {
            for (columnNumber in 0 until this.columns - CHECK_WINDOW) {
                if (row[columnNumber] == symbol
                    && row[columnNumber + 1] == symbol
                    && row[columnNumber + 2] == symbol
                    && row[columnNumber + 3] == symbol
                ) {
                    return true
                }
            }
        }
        return false
    }

    private fun isGameWonVertically(symbol: String): Boolean {
        for (columnNumber in 0 until this.columns) {
            for (rowNumber in 0 until this.rows - CHECK_WINDOW) {
                if (this.board[rowNumber][columnNumber] == symbol
                    && this.board[rowNumber + 1][columnNumber] == symbol
                    && this.board[rowNumber + 2][columnNumber] == symbol
                    && this.board[rowNumber + 3][columnNumber] == symbol
                ) {
                    return true
                }
            }
        }
        return false
    }

    private fun isGameWonDiagonally(symbol: String): Boolean {
        for (rowNumber in this.rows - 1 downTo CHECK_WINDOW) {
            for (columnNumber in 0 until this.columns - CHECK_WINDOW) {
                if (this.board[rowNumber][columnNumber] == symbol
                    && this.board[rowNumber - 1][columnNumber + 1] == symbol
                    && this.board[rowNumber - 2][columnNumber + 2] == symbol
                    && this.board[rowNumber - 3][columnNumber + 3] == symbol
                ) {
                    return true
                }
            }
        }
        for (rowNumber in 0 until this.rows - CHECK_WINDOW) {
            for (columnNumber in 0 until this.columns - CHECK_WINDOW) {
                if (this.board[rowNumber][columnNumber] == symbol
                    && this.board[rowNumber + 1][columnNumber + 1] == symbol
                    && this.board[rowNumber + 2][columnNumber + 2] == symbol
                    && this.board[rowNumber + 3][columnNumber + 3] == symbol
                ) {
                    return true
                }
            }
        }
        return false
    }

    fun resetGame() {
        this.board = MutableList(this.rows) { MutableList(this.columns) { " " } }
        this.filledCells = 0
        this.isGameFinished = false
    }

    fun printScore() {
        println("Score")
        print("${this.firstPlayer}: ${this.scoreFirstPlayer} ")
        println("${this.secondPlayer}: ${this.scoreSecondPlayer}")
    }
}
