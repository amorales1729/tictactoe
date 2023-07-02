package cpsc2150.extendedTicTacToe.models;

public interface IGameBoard {

    /**
     * The interface for classes/methods to make a Tic Tac Toe game.
     * <p>
     * Initialization Insures: each position in the game board is initialized as ' ' (empty spaces) AND
     *                         MIN_ROWS <= rows <= MAX_ROWS AND MIN_COLS <= columns <= MAX_COLS AND MIN_NUM_TO_WIN <= numToWin <= MAX_NUM_TO_WIN
     * Defines: numberOfRows: Z
     *          numberOfColumns: Z
     *          numToWin: Z
     * <p>
     * Constraints: MIN_ROWS <= numberOfRows <= MAX_ROWS
     *              MIN_COLS <= numberOfColumns <= MAX_COLS
     *              MIN_NUM_TO_WIN <= numToWin <= MAX_NUM_TO_WIN
     */
    public static final int MAX_ROWS = 100;
    public static final int MIN_ROWS = 3;
    public static final int MAX_COLS = 100;
    public static final int MIN_COLS = 3;
    public static final int MAX_NUM_TO_WIN = 25;
    public static final int MIN_NUM_TO_WIN = 3;

    /**
     * Checks to see if specified space is available.
     *
     * @param pos position user specified in gameGrid
     * @return true if pos = empty, else false
     * @pre pos >= gameGrid[0][0] AND pos <= gameGrid[getNumRows()][getNumCols()]
     * @post [if {@code pos} is empty, checkSpace = true], else checkSpace = false
     */

    public default boolean checkSpace(BoardPosition pos) {

        if ((pos.getRow() >= 0 && pos.getRow() < getNumRows()) && (pos.getColumn() >= 0 && pos.getColumn() < getNumColumns())) {
            if (whatsAtPos(pos) == ' ') {
                return true;
            }
        }
        return false;
    }

    /**
     * Places character marker on position specified by user.
     *
     * @param marker uses X or O to mark position
     * @param player specifies which player
     * @pre pos = ' ' (empty)
     * @post pos = marker
     */

    public void placeMarker(BoardPosition marker, char player);

    /**
     * Checks to see if there is a winner (horizontally, diagonally, and vertically).
     *
     * @param lastPos last position
     * @return winner = true; else false
     * @pre {@code lastPos} < NUM_TO_WIN
     * @post checkForWinner = true iff checkHorizontalWin = true OR checkVerticalWin = true OR checkDiagonalWin = true
     *       else, checkForWinner = false;
     */

    public default boolean checkForWinner(BoardPosition lastPos) {
        char playerMarker = whatsAtPos(lastPos);
        if(checkHorizontalWin(lastPos, playerMarker) || checkVerticalWin(lastPos, playerMarker) || checkDiagonalWin(lastPos, playerMarker)){
            return true;
        }
        return false;
    }

    /**
     * Checks if there is a draw.
     *
     * @return draw = true; else false
     * @pre none
     * @post if gameGrid == full, return true; else return false
     */

    public default boolean checkForDraw(){
        for(int row = 0; row < getNumRows(); row++){
            for(int col = 0; col < getNumColumns(); col++){
                BoardPosition pos = new BoardPosition(row, col);
                if(whatsAtPos(pos) == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks for win horizontally.
     *
     * @param lastPos last position
     * @param player  specifies which player
     * @return win = true; else false
     * @pre lastPos < NUM_TO_WIN (horizontally)
     * @post checkHorizontalWin = true if {@code winCount} >= NUM_TO_WIN (horizontally); else false
     */

    public default boolean checkHorizontalWin(BoardPosition lastPos, char player) {
        int winCount = -1;
        int row = lastPos.getRow();

        for (int col = lastPos.getColumn(); col < getNumColumns(); col++) {
            BoardPosition pos = new BoardPosition(row, col);
            if (whatsAtPos(pos) == player) {
                winCount++;
                if(winCount >= getNumToWin()){
                    return true;
                }
            }
            else{
                break;
            }
        }
        for (int col = lastPos.getColumn(); col >= 0; col--) {
            BoardPosition pos = new BoardPosition(row, col);
            if (whatsAtPos(pos) == player) {
                winCount++;
                if(winCount >= getNumToWin()){
                    return true;
                }
            }
            else{
                winCount = -1;
            }
        }
        return false;
    }

    /**
     * Checks for win vertically.
     *
     * @param lastPos last position
     * @param player  specifies which player
     * @return win = true; else false
     * @pre lastPos < NUM_TO_WIN (vertically)
     * @post checkVerticalWin = true if {@code winCount} >= NUM_TO_WIN (vertically); else false
     */

    public default boolean checkVerticalWin(BoardPosition lastPos, char player) {
        int winCount = -1;
        int col = lastPos.getColumn();

        for (int row = lastPos.getRow(); row < getNumColumns(); row++) {
            BoardPosition pos = new BoardPosition(row, col);
            if (whatsAtPos(pos) == player) {
                winCount++;
                if(winCount >= getNumToWin()){
                    return true;
                }
            }
            else{
                break;
            }
        }
        for (int row = lastPos.getRow(); row >= 0; row--) {
            BoardPosition pos = new BoardPosition(row, col);
            if (whatsAtPos(pos) == player) {
                winCount++;
                if(winCount >= getNumToWin()){
                    return true;
                }
            }
            else{
                winCount = -1;
            }
        }
        return false;
    }

    /**
     * Checks for win diagonally.
     *
     * @param lastPos last position
     * @param player  specifies which player
     * @return win = true; else false
     * @pre lastPos < NUM_TO_WIN (diagonally left or right)
     * @post checkDiagonalWin = true if {@code winCount} >= NUM_TO_WIN (diagonally left or right); else false
     */

    public default boolean checkDiagonalWin(BoardPosition lastPos, char player) {
        int winCount = 1;
        BoardPosition pos;

        // top left to bottom right ==> \
        int row = lastPos.getRow() + 1;
        int col = lastPos.getColumn() + 1;
        for(int i = row; i < getNumRows(); i++) {
            if(col >= 0 && col < getNumColumns()) {
                pos = new BoardPosition(i, col);
                if (whatsAtPos(pos) == player) {
                    winCount++;
                    if (winCount >= getNumToWin()) {
                        return true;
                    }
                }
                else {
                    break;
                }
            }
            col++;
        }

        row = lastPos.getRow() - 1;
        col = lastPos.getColumn() - 1;
        for(int i = row; i >= 0; i--) {
            if(col >= 0 && col < getNumColumns()) {
                pos = new BoardPosition(i, col);
                if (whatsAtPos(pos) == player) {
                    winCount++;
                    if (winCount >= getNumToWin()) {
                        return true;
                    }
                } else {
                    break;
                }
            }
            col--;
        }
        // top right to bottom left ==> /
        winCount = 1;
        row = lastPos.getRow() + 1;
        col = lastPos.getColumn() - 1;
        for(int i = row; i < getNumRows(); i++) {
            if(col >= 0 && col < getNumColumns()) {
                pos = new BoardPosition(i, col);
                if (whatsAtPos(pos) == player) {
                    winCount++;
                    if (winCount >= getNumToWin()) {
                        return true;
                    }
                }
                else {
                    break;
                }
            }
            col--;
        }

        row = lastPos.getRow() - 1;
        col = lastPos.getColumn() + 1;
        for(int i = row; i >= 0; i--) {
            if(col >= 0 && col < getNumColumns()) {
                pos = new BoardPosition(i, col);
                if (whatsAtPos(pos) == player) {
                    winCount++;
                    if (winCount >= getNumToWin()) {
                        return true;
                    }
                } else {
                    break;
                }
            }
            col++;
        }
        return false;
    }

    /**
     * Checks if player is at specified position.
     *
     * @param pos board position
     * @param player specifies which player
     * @return if player == pos, return true; else false
     * @pre pos >= gameGrid[0][0] && pos <= gameGrid[ROW][COL]
     * @post if player == pos, return true; else false
     */

    public default boolean isPlayerAtPos(BoardPosition pos, char player){
        if(whatsAtPos(pos) == player){
            return true;
        }
        return false;
    }
    /**
     * Returns what is in the GameBoard at user specified position.
     *
     * @param pos board position
     * @return player character OR ' ' (blank space)
     * @pre pos >= gameGrid[0][0] && pos <= gameGrid[ROW][COL]
     * @post returns player character ' ' (blank position)
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * Gets the number of rows for the grid.
     *
     * @return number of rows in the game
     * @pre none
     * @post getNumRows = numberOfRows
     */
    public int getNumRows();

    /**
     * Gets the number of columns for the grid.
     *
     * @return number of columns in the game
     * @pre none
     * @post getNumColumns = numberOfColumns
     */
    public int getNumColumns();

    /**
     * Gets the number of tokens you need to have in a row to win.
     *
     * @return number of tokens in a row needed to win
     * @pre none
     * @post getNumToWin = NUM_TO_WIN
     */
    public int getNumToWin();

}