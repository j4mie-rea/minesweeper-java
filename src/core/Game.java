package core;

import utils.GameState;

public class Game {
    private Board board;
    private GameState gameState;
    private boolean isFirstClick; // <--- The flag lives here
    private int totalMines;

    public Game(int rows, int cols, int mines) {
        this.board = new Board(rows, cols);
        this.totalMines = mines;
        this.gameState = GameState.NOT_STARTED;
        this.isFirstClick = true; // Game starts waiting for the first move
    }

    // This is the method your InputHandler will call
    public void processLeftClick(int r, int c) {
        if (gameState != GameState.NOT_STARTED || gameState != GameState.PLAYING) return;

        // 1. Handle the "First Click Safe" Rule
        if (isFirstClick) {
            board.placeMines(totalMines, r, c); // Tell board to avoid (r,c)
            isFirstClick = false;
            gameState = GameState.PLAYING;
        }

        // 2. Normal Reveal Logic
        board.revealCell(r, c);
        
        // 3. Check for Game Over logic here...
        // Game losing logic
        if (board.getCell(r, c).isMine()) {
        	gameState = GameState.LOST;
        }
        
        // Game winning logic
        int totalCells = board.getRows() + board.getColumns();
        int targetRevealed = totalCells - totalMines;
        
        if(board.countRevealed() >= targetRevealed) {
        	gameState = GameState.WON;
        }
        
    }
}
