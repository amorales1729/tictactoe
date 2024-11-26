package cpsc2150.extendedTicTacToe;

import cpsc2150.extendedTicTacToe.controllers.*;
import cpsc2150.extendedTicTacToe.views.*;

public final class TicTacToeGame {

    /**
     * <p>
     * This method is the main entry point into the program.
     * </p>
     *
     * @param args
     *            Command-line arguments (there shouldn't be any).
     */
    public static void main(String[] args) {
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}
