package cpsc2150.extendedTicTacToe.controllers;

import cpsc2150.extendedTicTacToe.models.*;
import cpsc2150.extendedTicTacToe.views.*;

import java.awt.event.ActionEvent;

/**
 * <p>
 * The {@link TicTacToeController} class will handle communication between our {@link TicTacToeView}
 * and our model ({@link IGameBoard} and {@link BoardPosition})
 * </p>
 *
 * <p>
 * This is where you will write code
 * <p>
 *
 * You will need to include your {@link BoardPosition} class, the {@link IGameBoard} interface
 * and both of the {@link IGameBoard} implementations from Project 4.
 * If your code was correct you will not need to make any changes to your {@link IGameBoard} implementation class.
 *
 * @version 2.0
 */
public class TicTacToeController {

    /**
     * <p>
     * The current game that is being played
     * </p>
     */
    private IGameBoard curGame;

    /**
     * <p>
     * The screen that provides our view
     * </p>
     */
    private TicTacToeView screen;

    /**
     * <p>
     * Constant for the maximum number of players.
     * </p>
     */
    public static final int MAX_PLAYERS = 10;

    /**
     * <p>
     * The number of players for this game. Note that our player tokens are hard coded.
     * </p>
     */
    private int numPlayers;

    /**
     * <p>
     * Boolean to figure out if the game will be played again or not.
     * </p>
     */
    boolean playAgain;

    /**
     * <p>
     * Array that holds all 10 possible player characters for the game.
     * </p>
     */
    char player [] = {'X', 'A', 'M', 'O', 'W', 'R', 'G', 'H', 'K', 'U'};

    /**
     * <p>
     * An iterator that will be going through the array of player characters to correctly place each character's marker.
     * </p>
     */
    int index;

    /**
     * <p>
     * Constant for the number 0 (mainly to not have any magic numbers).
     * </p>
     */
    public static final int ZERO = 0;

    /**
     * <p>
     * This creates a controller for running the Extended TicTacToe game
     * </p>
     *
     * @param model
     *      The board implementation
     * @param view
     *      The screen that is shown
     * @param np
     *      The number of players for this game.
     *
     * @post [ the controller will respond to actions on the view using the model.]
     */
    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        this.curGame = model;
        this.screen = view;
        this.numPlayers = np;

        playAgain = false;
        index = ZERO;
    }

    /**
     * <
     * This processes a button click from the view.
     * </p>
     *
     * @param row
     *      The row of the activated button
     * @param col
     *      The column of the activated button
     *
     * @post [ will allow the player to place a marker in the position if it is a valid space, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button ]
     */

    public void processButtonClick(int row, int col) {

        // starts new game if someone wins or draws and presses any button
        if (playAgain){
            newGame();
        }
        // check for valid space and sets player char
        BoardPosition pos = new BoardPosition(row, col);
        if(curGame.checkSpace(pos)) {
            curGame.placeMarker(pos, player[index]);
            screen.setMarker(row, col, player[index]);

            // check for winner and draw
            if (curGame.checkForWinner(pos)){
                screen.setMessage(player[index] + " is the winner!");
                playAgain = true;
            }
            else if (curGame.checkForDraw()){
                screen.setMessage("It is a tie!");
                playAgain = true;
            }
            index++; // increment
            if (index == numPlayers){
                index = ZERO;
            }
            if (!playAgain) {
                screen.setMessage("It is " + player[index] + "'s turn.");
            }
        }
        else{
            screen.setMessage("Space is unavailable.");
        }
    }

    /**
     * <p>
     * This method will start a new game by returning to the setup screen and controller
     * </p>
     *
     * @post [ a new game gets started ]
     */
    private void newGame() {
        //close the current screen
        screen.dispose();

        //start back at the set-up menu
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}