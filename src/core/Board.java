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
	
	//gets the number of rows
	public int getRows() {
		return this.rows;
	}
	
	//gets the number of columns
	public int getColumns() {
		return this.columns;
	}
	
	//creates the grid
	public void initialiseGrid() {
		for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                grid[r][c] = new Cell(); //creates a cell for this spot
            }
        }
	}
	
	//gets the status of a cell
	public Cell getCell(int r, int c) {
        if (r >= 0 && r < rows && c >= 0 && c < columns) {
            return grid[r][c];
        }
        return null;
    }
	
	//places mines whenever the first move is made
	public void placeMines(int totalMines, int safeRow, int safeCol) {
	    int minesPlaced = 0;
	    
	    while (minesPlaced < totalMines) {
	        //generates random coordinates
	        int r = (int) (Math.random() * rows);
	        int c = (int) (Math.random() * columns);

	        
	        
	        //makes the first move cell as well as neighbours safe
	        boolean isSafeZone = Math.abs(r - safeRow) <= 1 && Math.abs(c - safeCol) <= 1;

	        if (!grid[r][c].isMine() && !isSafeZone) {
	            grid[r][c].setMine(true);
	            minesPlaced++;
	        }
	    }
	    
	   
	    
	    //calculates the number of neighbours when the mines are placed
	    calculateNeighborCounts(); 
	}
	

	//calculates the number of neighbours (0-8)
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
	    //loops the 3x3 area around target cell
	    for (int i = -1; i <= 1; i++) {
	        for (int j = -1; j <= 1; j++) {
	            int checkRow = r + i;
	            int checkCol = c + j;
	            
	            //boundries to avoid crashes
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
	    //base case
	    if (r < 0 || r >= rows || c < 0 || c >= columns) return;
	    
	    //if cell is flagged, return
	    if (grid[r][c].isRevealed() || grid[r][c].isFlagged()) return;

	    //opens the cell
	    grid[r][c].setRevealed(true);

	    //recursive step if all neighbours are safe
	    if (grid[r][c].getNeighborMineCount() == 0 && !grid[r][c].isMine()) {
	        
	        //calls the same method on all 8 neighbours
	        revealCell(r - 1, c - 1); //top left
	        revealCell(r - 1, c    ); //top
	        revealCell(r - 1, c + 1); //top right
	        revealCell(r,     c - 1); //left
	        revealCell(r,     c + 1); //right
	        revealCell(r + 1, c - 1); //bottom left
	        revealCell(r + 1, c    ); //bottom
	        revealCell(r + 1, c + 1); //bottom right
	    }
	}
	
	//reveals all mines when the game is over
	public void revealAllMines() {
	    for (int r = 0; r < rows; r++) {
	        for (int c = 0; c < columns; c++) {
	            if (grid[r][c].isMine()) {
	                grid[r][c].setRevealed(true);
	            }
	        }
	    }
	}
	
	//counts the number of revealed cells
	public int countRevealed() {
	    int count = 0;
	    for (int r = 0; r < rows; r++) {
	        for (int c = 0; c < columns; c++) {
	            if (grid[r][c].isRevealed()) {
	                count++;
	            }
	        }
	    }
	    return count;
	}
	
	//flag placing logic
	public void placeFlag(int r, int c) {
		if (r >= 0 && r < rows && c >= 0 && c < columns) {
	        grid[r][c].toggleFlag();
		}
	}
}
