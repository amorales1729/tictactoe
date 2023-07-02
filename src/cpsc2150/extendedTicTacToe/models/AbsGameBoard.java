package cpsc2150.extendedTicTacToe.models;

public abstract class AbsGameBoard implements IGameBoard {
    /**
     * Overrides toString to print the game grid.
     *
     * @return string to show entire game board and player selections
     * @pre none
     * @post toString = [string of entire game board]
     */
    @Override
    public String toString() {
        String board = "    ";

        for (int i = 0; i < getNumColumns(); i++) {
            if (i < 9 && i < getNumColumns() - 1) {
                board += i + "| ";
            } else {
                board += i + "|";
            }
        }
        for (int i = 0; i < getNumRows(); i++) {
            if (i < 10) {
                board += "\n " + i;
            } else {
                board += "\n" + i;
            }
            for (int j = 0; j < getNumColumns(); j++) {
                BoardPosition pos = new BoardPosition(i, j);
                board += "|" + whatsAtPos(pos) + " ";
                if (j == getNumColumns() - 1) {
                    board += "|";
                }
            }
        }
        board += "\n";
        return board;
    }
}