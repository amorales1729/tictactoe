package cpsc2150.extendedTicTacToe.models;

import java.util.*;
public class GameBoardMem extends AbsGameBoard implements IGameBoard {

    /**
     * Keeps track of the game board.
     * @invariant board >= [MAX_COL][MIN_COL] AND board <= [MAX_ROW][MAX_COL] AND MIN_NUM_TO_WIN <= numToWin <= MAX_NUM_TO_WIN
     * @correspondence row = getNumRows() AND col = getNumColumns() AND board = [ new hashmap ] AND numToWin = getNumToWin()
     */
    Map <Character, List<BoardPosition>> board;
    private int row;
    private int col;
    private int numToWin;

    /**
     * Initializes game board.
     * @param numRows total number of rows
     * @param numCols total number of columns
     * @param tokensToWIn number of tokens in a row to win
     * @pre rows >= MIN_ROWS AND rows <= MAX_ROWS AND col >= MIN_COLS AND col <= MAX_COLS
     * @post row = {@param numRows} AND col = {@param numCols} AND numToWin = {@param tokensToWin}
     *       AND board = [new hashmap]
     */
    public GameBoardMem(int numRows, int numCols, int tokensToWIn) {
        row = numRows;
        col = numCols;
        numToWin = tokensToWIn;

        board = new HashMap<>();
    }
        public void placeMarker (BoardPosition marker, char player){
            if(board.containsKey(player)){
                List<BoardPosition> list = board.get(player);
                list.add(marker);
            }
            else{
                List<BoardPosition> list = new ArrayList<>();
                list.add(marker);
                board.put(player, list);
            }
        }
        public char whatsAtPos (BoardPosition pos){
            for (Map.Entry<Character, List<BoardPosition>> m: board.entrySet()) {
                if (m.getValue().contains(pos)) {
                    return m.getKey();
                }
            }
            return ' ';
        }

        @Override
        public boolean isPlayerAtPos (BoardPosition pos, char player){
            for (Map.Entry<Character, List<BoardPosition>> m: board.entrySet()) {
                if(m.getKey() == player && m.getValue().contains(pos)){
                    return true;
                }
            }
            return false;
        }
        public int getNumRows(){return row;}
        public int getNumColumns () {return col;}
        public int getNumToWin () {return numToWin;}
}

