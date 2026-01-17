package input;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import core.Game;
import ui.BoardPanel;
import utils.GameState;

public class InputHandler extends MouseAdapter {
	private Game game;
	private BoardPanel view;
	
	public InputHandler(Game game, BoardPanel view) {
		this.game = game;
		this.view = view;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		JButton clickedButton = (JButton) e.getSource();
		
		int[] coords = view.getButtonCoords(clickedButton);
		int r = coords[0];
		int c = coords[1];
		
		if (SwingUtilities.isLeftMouseButton(e)) {
            game.processLeftClick(r, c);//when the left click is input, processes left click in the game class
            
        } else if (SwingUtilities.isRightMouseButton(e)) {
            game.processRightClick(r, c);//when the right click is input, processes right click in the game class
        }
		
		view.refreshBoard();//refreshes the board after a mouse button press
		
		//check for game win
		if(game.getGameState() == GameState.WON) {
			String message = "You Win!\n"
					+ "Time: " + game.getTime() + "\n"
					+ "Moves: " + game.getMoveCount();
			JOptionPane.showMessageDialog(view, message, "Victory!", JOptionPane.INFORMATION_MESSAGE);
		} else if(game.getGameState() == GameState.LOST) {
			JOptionPane.showMessageDialog(view, "Boom! You hit a mine.", "Game Over", JOptionPane.ERROR_MESSAGE);
		}
	}
}
