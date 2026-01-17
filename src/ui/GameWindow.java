package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import core.Game;

//creates the desktop game window where the game will be played
public class GameWindow extends JFrame {
	private BoardPanel boardPanel;
	private Game game;
	
	public GameWindow(Game game) {
		this.game = game;
		
		setTitle("Minesweeper");//sets the window title to "Minesweeper"
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		boardPanel = new BoardPanel(game);
		add(boardPanel, BorderLayout.CENTER);
		
		JLabel statusLabel = new JLabel("Welcome to Minesweeper!");//adds this label to the top of the window
		statusLabel.setHorizontalAlignment(JLabel.CENTER);
		add(statusLabel, BorderLayout.NORTH);
		
		setResizable(false);//stops the window from being resized
		pack();
		setLocationRelativeTo(null);
		setVisible(true);//makes the window visible so the game can be played
	}
	
	//getter for the app class
	public BoardPanel getBoardPanel() {
        return boardPanel;
    }
}
