package core;

public class Cell {
	private boolean isMine;
	private boolean isRevealed;
	private boolean isFlagged;
	private int neighbourMineCount;
	
	public Cell() {
		isMine = false;
		isRevealed = false;
		isFlagged = false;
		neighbourMineCount = 0;
	}
	
	public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void toggleFlag() {
        // Simple switch: if true, make false. If false, make true.
        // Important: You can usually only flag a cell if it is NOT revealed yet.
        if (!isRevealed) {
            isFlagged = !isFlagged;
        }
    }

    public int getNeighborMineCount() {
        return neighbourMineCount;
    }

    public void setNeighborMineCount(int count) {
        neighbourMineCount = count;
    }
    
    // Optional: Useful for debugging in Console before you have a UI
    @Override
    public String toString() {
        if (isFlagged) return "F";
        if (!isRevealed) return "?";
        if (isMine) return "X";
        return String.valueOf(neighbourMineCount);
    }
	
}
