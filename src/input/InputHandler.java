package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import core.Game;
import utils.GameState;
import ui.GameWindow;

public class InputHandler extends MouseAdapter {
	private Game game;
	private GameWindow window;

	public InputHandler(Game game, GameWindow window) {
		this.game = game;
		this.window = window;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JButton clickedButton = (JButton) e.getSource();

		int[] coords = window.getBoardPanel().getButtonCoords(clickedButton);
		int r = coords[0];
		int c = coords[1];

		if (SwingUtilities.isLeftMouseButton(e)) {
			game.processLeftClick(r, c);// when the left click is input, processes left click in the game class

		} else if (SwingUtilities.isRightMouseButton(e)) {
			game.processRightClick(r, c);// when the right click is input, processes right click in the game class
		}

		window.getBoardPanel().refreshBoard();// refreshes the board after a mouse button press
		window.updateHeader();

		// check for game win/lost
		if (game.getGameState() == GameState.WON || game.getGameState() == GameState.LOST) {
			showGameOverDialog();
		}
	}

	public void showGameOverDialog() {
		String title;
	    String message;
	    int messageType;

	    //messages for win/lost
	    if (game.getGameState() == GameState.WON) {
	        title = "Victory!";
	        message = "You Win!\n" 
	                + "Time: " + game.getTime() + "\n"
	                + "Moves: " + game.getMoveCount();
	        messageType = JOptionPane.INFORMATION_MESSAGE;
	    } else {
	        title = "Game Over";
	        message = "Boom! You hit a mine.";
	        messageType = JOptionPane.ERROR_MESSAGE;
	    }

	    //buttons for game over
	    Object[] options = {"Play Again", "Exit"};

	    //shows dialog options after game end
	    int choice = JOptionPane.showOptionDialog(
	        window,                   //parent component
	        message,                //text
	        title,                  //title bar
	        JOptionPane.YES_NO_OPTION,
	        messageType,            //icon type
	        null,                   //custom icon (none)
	        options,                //custom buttons
	        options[0]              //default button (play again)
	    );

	    //handling of user choice
	    if (choice == 0) {
	        //user clicked play again
	        game.reset();
	        window.getBoardPanel().refreshBoard(); //redraws empty grid
	    } else {
	        //closes the game when user hit exit
	        System.exit(0);
	    }
	}
}
