package core;

public class GameStatistics {
    private long startTime;
    private long endTime;
    private int movesMade;
    private boolean isRunning;

    public GameStatistics() {
        reset();
    }

    public void startTimer() {
        this.startTime = System.currentTimeMillis();
        this.isRunning = true;
    }

    public void stopTimer() {
        this.endTime = System.currentTimeMillis();
        this.isRunning = false;
    }
    
    public void reset() {
        this.startTime = 0;
        this.endTime = 0;
        this.movesMade = 0;
        this.isRunning = false;
    }

    public void incrementMoves() {
        movesMade++;
    }

    public int getMovesMade() {
        return movesMade;
    }

    // Logic Fix: Calculate time based on whether game is currently running
    public long getTimeElapsedMillis() {
        if (isRunning) {
            return System.currentTimeMillis() - startTime;
        } else {
            // If game is over (or hasn't started), return the fixed duration
            return endTime - startTime;
        }
    }

    public int getSeconds() {
        // Use the smart getter above
        long millis = getTimeElapsedMillis(); 
        if (millis < 0) return 0; // Safety check if game hasn't started
        return (int) ((millis / 1000) % 60);
    }

    public int getMinutes() {
        long millis = getTimeElapsedMillis();
        if (millis < 0) return 0;
        return (int) (millis / 60000);
    }
}