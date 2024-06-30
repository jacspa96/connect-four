package connectfour

data class Game(
    val rows: Int,
    val columns: Int,
    val board: MutableList<MutableList<String>>,
    val firstPlayer: String,
    val secondPlayer: String
)
