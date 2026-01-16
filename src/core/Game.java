package core;

public class Game {
    private Board board;
    private boolean isGameOver;
    private boolean isFirstClick; // <--- The flag lives here
    private int totalMines;

    public Game(int rows, int cols, int mines) {
        this.board = new Board(rows, cols);
        this.totalMines = mines;
        this.isGameOver = false;
        this.isFirstClick = true; // Game starts waiting for the first move
    }

    // This is the method your InputHandler will call
    public void processLeftClick(int r, int c) {
        if (isGameOver) return;

        // 1. Handle the "First Click Safe" Rule
        if (isFirstClick) {
            board.placeMines(totalMines, r, c); // Tell board to avoid (r,c)
            isFirstClick = false;
        }

        // 2. Normal Reveal Logic
        board.revealCell(r, c);
        
        // 3. Check for Game Over logic here...
    }
}
