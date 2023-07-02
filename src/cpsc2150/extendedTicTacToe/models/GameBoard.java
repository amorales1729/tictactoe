package cpsc2150.extendedTicTacToe.models;

public class GameBoard extends AbsGameBoard implements IGameBoard {

    /**
     * Keeps track of the game board.
     * @invariant board >= [MAX_COL][MIN_COL] AND board <= [MAX_ROW][MAX_COL] AND MIN_NUM_TO_WIN <= numToWin <= MAX_NUM_TO_WIN
     * @correspondence row = getNumRows() AND col = getNumColumns() AND gameGrid = [gameGrid [row][col]] AND numToWin = getNumToWin()
     */

    private char[][] gameGrid;
    private int row;
    private int col;
    private int numToWin;

    /**
     * Initializes game board with each position becoming a ' ' (blank space).
     * @param numRows total number of rows
     * @param numCols total number of columns
     * @param tokensToWIn number of tokens in a row to win
     * @pre rows >= MIN_ROW AND rows <= MAX_ROW AND col >= MIN_COL AND col <= MAX_COL AND MIN_NUM_TO_WIN <= numToWin <= MAX_NUM_TO_WIN
     * @post row = {@param numRows} AND col = {@param numCols} AND numToWin = {@param tokensToWin}
     *       AND gameGrid = [ initialized all coordinates to ' ' (blank characters)
     */
    public GameBoard(int numRows, int numCols, int tokensToWIn){
        row = numRows;
        col = numCols;
        numToWin = tokensToWIn;

        gameGrid = new char[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++) {
                gameGrid[i][j] = ' ';
            }
        }
    }
    // places marker (X or O) on position specified by user
    public void placeMarker(BoardPosition marker, char player){
        int row = marker.getRow();
        int col = marker.getColumn();
        gameGrid[row][col] = player;
    }
    public char whatsAtPos(BoardPosition pos){
        return gameGrid[pos.getRow()][pos.getColumn()];
    }
    public int getNumRows(){return row;}
    public int getNumColumns(){return col;}
    public int getNumToWin() {return numToWin;}
}
