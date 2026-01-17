package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import core.Game;
import utils.GameState;

public class BoardPanel extends JPanel {
	private JButton[][] buttons;
	private int rows;
	private int columns;
	private Game game;

	public BoardPanel(Game game) {
		this.game = game;
		this.rows = game.getBoard().getRows();
		this.columns = game.getBoard().getColumns();

		setLayout(new GridLayout(rows, columns));
		buttons = new JButton[rows][columns];//creates buttons for each cell

		initializeButtons();
	}

	private void initializeButtons() {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				JButton btn = new JButton();
				btn.setPreferredSize(new Dimension(40, 40));//dimension of the buttons is 40x40 pixels
				btn.setFocusable(false); //stops the text within the buttons from being highlighted
				btn.setMargin(new java.awt.Insets(0, 0, 0, 0));

				
				btn.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));

				
				btn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
				//saves the buttons to the array
				buttons[r][c] = btn;

				//adds buttons to panel so they can be shown in the window
				add(btn);
			}
		}
	}

	public int[] getButtonCoords(JButton btn) {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				if (buttons[r][c] == btn) {
					return new int[] { r, c };
				}
			}
		}
		return null;
	}

	//refreshes the board when an action takes place
	public void refreshBoard() {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				JButton btn = buttons[r][c];

				core.Cell cell = game.getBoard().getCell(r, c);

				if (cell.isRevealed()) {
					btn.setEnabled(false);//stops the user from clicking revealed cells

					if (cell.isMine()) {
						if (game.getGameState() == GameState.WON) {
					        btn.setBackground(Color.GREEN);//sets mine background colour to green if won
					        btn.setText("M");
					    } else {
					        btn.setBackground(Color.RED);//sets mine background colour to red if lost
					        btn.setText("X");
					    }
					} else {
						if (cell.getNeighborMineCount() > 0) {
							btn.setText(String.valueOf(cell.getNeighborMineCount())); //shows the number of neighbouring mines
						} else {
							btn.setText("");//shows when there are no neighbours
						}
					}
				} else if (cell.isFlagged()) {
					btn.setText("F");//f where the user has flagged
				} else {//resets when a user unflags
					btn.setText("");
					btn.setBackground(null);
					btn.setForeground(Color.BLACK);
					btn.setEnabled(true);
				}
			}
		}
	}
	
	//getter for the app class
	public JButton[][] getButtons(){
		return buttons;
	}
}
