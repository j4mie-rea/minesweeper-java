package utils;

public enum GameState {
	PLAYING("Playing"), WON("Win"), LOST("Lose"), NOT_STARTED("Not Started");

	private String gameStateStr;

	private GameState(String gameState) {
		gameStateStr = gameState;
	}

	public String toString() {
		return gameStateStr;

	}
}
