package cpsc2150.extendedTicTacToe.models;

public class BoardPosition {
    /**
     * Keeps track of an individual cell for a specified board size.
     *
     * @invariant row >= 0 && row < MAX_ROWS AND column >=0 && column < MAX_COLS
     */

    private char player;
    private int [][] lastPos;
    private char marker;
    private int rowPosition;
    private int colPosition;

    /**
     * Constructor to initialize BoardPosition to set row and column position.
     *
     * @param row position in row
     * @param column position in column
     *
     * @pre row >= 0 && row < ROW AND column >= 0 && column < COL
     * @post row = rowPosition and column = colPosition
     * */

    public BoardPosition(int row, int column) {
        rowPosition = row;
        colPosition = column;
    }

    /**
     * Gets position for row.
     *
     * @return rowPosition
     * @pre row >= 0 AND row < ROW
     * @post getRow = rowPosition
     */
    public int getRow () {
        return rowPosition;
    }

    /**
     * Gets position for column.
     *
     * @return colPosition
     * @pre column >= 0 AND column < COL
     * @post getColumn = column
     */
    public int getColumn() {return colPosition;}

    /**
     * Overriding equals to compare board position objects.
     * @return true if equal, else false
     */
    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof BoardPosition)){
            return false;
        }
        BoardPosition board = (BoardPosition) o;

        if(this.rowPosition == board.rowPosition && this.colPosition == board.colPosition){
            return true;
        }
        return false;
    }

    /**
     * Overriding toString to show row and column of position.
     *
     * @return prints string of row + column
     */
    @Override
    public String toString() {return getRow() + "," + getColumn();}
}
