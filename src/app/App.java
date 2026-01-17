package app;

import core.Game;
import ui.GameWindow;
import input.InputHandler;
import javax.swing.JButton;

//class that links the classes
public class App {
    public static void main(String[] args) {
        //creates the game
        Game game = new Game(10, 10, 10);

        //creates the game window
        GameWindow window = new GameWindow(game);

        //allows left and right click to be inputs
        InputHandler input = new InputHandler(game, window.getBoardPanel());

        //connects the inputs to the ui
        for (JButton[] row : window.getBoardPanel().getButtons()) {
            for (JButton btn : row) {
                btn.addMouseListener(input);
            }
        }
    }
}
