package core;

import utils.GameState;

public class Game {
    private Board board;
    private GameStatistics stats;
    private GameState gameState;
    private boolean isFirstClick;
    private int totalMines;

    public Game(int rows, int cols, int mines) {
        this.board = new Board(rows, cols);
        this.totalMines = mines;
        this.gameState = GameState.NOT_STARTED;
        this.isFirstClick = true; //game is still waiting for first click
        this.stats = new GameStatistics();
    }

    //what the game does when the user does a left click button press
    public void processLeftClick(int r, int c) {
        if (gameState == GameState.WON || gameState == GameState.LOST) return;

        //for first click, ensures that the cell is safe as well as neighbouring cells
        if (isFirstClick) {
        	stats.reset();
        	stats.startTimer();
            board.placeMines(totalMines, r, c); //places mines around the clicked cell and neighbours
            isFirstClick = false;
            gameState = GameState.PLAYING;
        }

        //reveal logic
        board.revealCell(r, c);
        stats.incrementMoves();
        
        //game over logic
        //game lost
        if (board.getCell(r, c).isMine()) {
        	gameState = GameState.LOST;
        	board.revealAllMines();
        	stats.stopTimer();
        }
        
        //game won
        int totalCells = board.getRows() * board.getColumns();
        int targetRevealed = totalCells - totalMines;
        
        if(board.countRevealed() >= targetRevealed) {
        	gameState = GameState.WON;
        	stats.stopTimer();
        	board.revealAllMines();
        }
        
    }
    
    public void processRightClick(int r, int c) {
        if (gameState == GameState.WON || gameState == GameState.LOST) return;

      
        //flag placing logic
        board.placeFlag(r, c);
        boolean isNowFlagged = board.getCell(r, c).isFlagged();
        
        //updates flag counter
        if (isNowFlagged) {
            stats.incrementFlags(); //adds when flagged
        } else {
            stats.decrementFlags(); //removes when unflagged
        }
    }
    
 
    //getters for ui
    public Board getBoard() { return board; }
    public GameState getGameState() { return gameState; }
    
    //changes time to string
    public String getTime() {
        int min = stats.getMinutes();
        int sec = stats.getSeconds();
        
        //String.format to format the string in the form MM:SS
        return String.format("%02d:%02d", min, sec);
    }
    
    public int getMoveCount() {
        return stats.getMovesMade();
    }
    
    public int getFlagsPlaced() {
    	return stats.getFlagsPlaced();
    }
    
    public int getFlagsRemaining() {
    	return totalMines - stats.getFlagsPlaced();
    }
}
