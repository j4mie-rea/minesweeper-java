package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import core.Game;

//creates the desktop game window where the game will be played
public class GameWindow extends JFrame {
	private BoardPanel boardPanel;
	private Game game;
	private JLabel infoLabel;
	
	public GameWindow(Game game) {
		this.game = game;
		
		setTitle("Minesweeper");//sets the window title to "Minesweeper"
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		//shows number of flags remaining
		infoLabel = new JLabel("Flags left: " + game.getFlagsRemaining());
		infoLabel.setHorizontalAlignment(JLabel.CENTER);
		infoLabel.setFont(new Font("Arial", Font.BOLD, 16));
		add(infoLabel, BorderLayout.NORTH);
		
		boardPanel = new BoardPanel(game);
		add(boardPanel, BorderLayout.CENTER);
		
		setResizable(false);//stops the window from being resized
		pack();
		setLocationRelativeTo(null);
		setVisible(true);//makes the window visible so the game can be played
	}
	
	//getter for the app class
	public BoardPanel getBoardPanel() {
        return boardPanel;
    }
	
	//updates text when a flag is placed
	public void updateHeader() {
	    int remaining = game.getFlagsRemaining();

	    //text for flags remaining label
	    infoLabel.setText("Flags Left: " + remaining);
	    
	    //turns flag icon red when too many are used
	    if (remaining < 0) {
	        infoLabel.setForeground(java.awt.Color.RED);
	    } else {
	        infoLabel.setForeground(java.awt.Color.BLACK);
	    }
	}
}
