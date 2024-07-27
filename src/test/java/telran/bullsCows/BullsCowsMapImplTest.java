package telran.bullsCows;


public class BullsCowsMapImplTest extends BullsCowsMapImpl {
	
	public long createNewGame(String answer) {
		long gameID = getGameID();
		games.put(gameID, new Game(gameID, answer));
		return gameID;
	}
}
