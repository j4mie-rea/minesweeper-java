package core;

public class Board {
	private Cell[][] grid;
	private int rows;
	private int columns;
	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		
		grid = new Cell[rows][columns];
		
		initialiseGrid();
	}
	
	public void initialiseGrid() {
		for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                grid[r][c] = new Cell(); // Create a new Cell for this spot
            }
        }
	}
	public Cell getCell(int r, int c) {
        if (r >= 0 && r < rows && c >= 0 && c < columns) {
            return grid[r][c];
        }
        return null;
    }
	
	public void placeMines(int totalMines, int safeRow, int safeCol) {
	    int minesPlaced = 0;
	    
	    while (minesPlaced < totalMines) {
	        // 1. Generate random coordinates
	        int r = (int) (Math.random() * rows);
	        int c = (int) (Math.random() * columns);

	        // 2. Check if this spot is valid
	        // Condition A: It must not already have a mine
	        // Condition B: It must NOT be the "Safe Zone" (the user's first click)
	        boolean isSafeZone = (r == safeRow && c == safeCol);
	        
	        // Optional Pro-Tip: Make the neighbors safe too for a guaranteed opening!
	        // boolean isSafeZone = Math.abs(r - safeRow) <= 1 && Math.abs(c - safeCol) <= 1;

	        if (!grid[r][c].isMine() && !isSafeZone) {
	            grid[r][c].setMine(true);
	            minesPlaced++;
	        }
	    }
	    
	    // After mines are placed, we must calculate the numbers
	    calculateNeighborCounts(); 
	}

	// You also need a method to calculate the numbers (0-8)
	private void calculateNeighborCounts() {
	    for (int r = 0; r < rows; r++) {
	        for (int c = 0; c < columns; c++) {
	            if (!grid[r][c].isMine()) {
	                int count = countMinesAround(r, c);
	                grid[r][c].setNeighborMineCount(count);
	            }
	        }
	    }
	}

	private int countMinesAround(int r, int c) {
	    int count = 0;
	    // Loop through the 3x3 block surrounding the cell
	    for (int i = -1; i <= 1; i++) {
	        for (int j = -1; j <= 1; j++) {
	            int checkRow = r + i;
	            int checkCol = c + j;
	            
	            // Check boundaries first to avoid crash
	            if (checkRow >= 0 && checkRow < rows && checkCol >= 0 && checkCol < columns) {
	                if (grid[checkRow][checkCol].isMine()) {
	                    count++;
	                }
	            }
	        }
	    }
	    return count;
	}
	
	public void revealCell(int r, int c) {
	    // 1. BASE CASES - The "Stop" Conditions
	    // If we are off the grid, stop.
	    if (r < 0 || r >= rows || c < 0 || c >= columns) return;
	    
	    // If the cell is already open or flagged, stop. (CRITICAL to prevent crashes)
	    if (grid[r][c].isRevealed() || grid[r][c].isFlagged()) return;

	    // 2. REVEAL - Open the cell
	    grid[r][c].setRevealed(true);

	    // 3. RECURSION - The "Magic" Step
	    // Only spread if the current cell is a '0' (no mines nearby)
	    if (grid[r][c].getNeighborMineCount() == 0 && !grid[r][c].isMine()) {
	        
	        // Call this SAME method on all 8 neighbours
	        revealCell(r - 1, c - 1); // Top-Left
	        revealCell(r - 1, c    ); // Top
	        revealCell(r - 1, c + 1); // Top-Right
	        revealCell(r,     c - 1); // Left
	        revealCell(r,     c + 1); // Right
	        revealCell(r + 1, c - 1); // Bottom-Left
	        revealCell(r + 1, c    ); // Bottom
	        revealCell(r + 1, c + 1); // Bottom-Right
	    }
	}
}
