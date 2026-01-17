package core;

public class GameStatistics {
    private long startTime;
    private long endTime;
    private int movesMade;
    private int flagsPlaced;
    private boolean isRunning;

    //constructor resets game stats
    public GameStatistics() {
        reset();
    }

    //starts timer when game is played
    public void startTimer() {
        this.startTime = System.currentTimeMillis();
        this.isRunning = true;
    }

    //ends timer when all mines are located or a mine is revealed
    public void stopTimer() {
        this.endTime = System.currentTimeMillis();
        this.isRunning = false;
    }
    
    //starting values
    public void reset() {
        this.startTime = 0;
        this.endTime = 0;
        this.movesMade = 0;
        this.flagsPlaced = 0;
        this.isRunning = false;
    }

    //counter for moves made
    public void incrementMoves() {
        movesMade++;
    }
    
    //adds when a flag is placed
    public void incrementFlags() {
        flagsPlaced++;
    }
    
    //takes away when a cell is unflagged
    public void decrementFlags() {
        flagsPlaced--;
    }
    
    //returns number of flags placed
    public int getFlagsPlaced() {
        return flagsPlaced;
    }

    //returns number of moves made
    public int getMovesMade() {
        return movesMade;
    }

    //gets game time
    public long getTimeElapsedMillis() {
        if (isRunning) {
            return System.currentTimeMillis() - startTime;
        } else {
            //return the fixed time duration when game is not running
            return endTime - startTime;
        }
    }

    //gets seconds
    public int getSeconds() {
        long millis = getTimeElapsedMillis(); 
        if (millis < 0) return 0;
        return (int) ((millis / 1000) % 60);
    }

    //gets mins
    public int getMinutes() {
        long millis = getTimeElapsedMillis();
        if (millis < 0) return 0;
        return (int) (millis / 60000);
    }
}