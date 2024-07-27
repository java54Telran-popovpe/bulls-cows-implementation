package telran.bullsCows;

import java.util.*;

public class BullsCowsMapImpl implements BullsCowsService {
	
	private static final String UNKNOWN_GAME_ID_MESSAGE = "Trying to get data for a game with unknown ID %s";
	protected HashMap<Long, Game> games = new HashMap<>();

	@Override
	public long createNewGame() {
		long gameID = getGameID();
		games.put(gameID, new Game(gameID));
		return gameID;
	}

	protected long getGameID() {
		Random random = new Random();
		long result;
		do {
			result = random.nextLong();
		} while ( games.containsKey(result));
		return result;
	}

	@Override
	public List<MoveResult> getResults(long gameID, Move move) {
		return getGameWithIDOf(gameID).moveProcess(move);
	}

	@Override
	public boolean isGameOver(long gameID) {
		return getGameWithIDOf(gameID).isOver();
	}

	private Game getGameWithIDOf(long gameID) {
		Game result = games.get(gameID);
		if ( result == null ) {
			throw new IllegalArgumentException(String.format(UNKNOWN_GAME_ID_MESSAGE, Long.toUnsignedString(gameID)));
		}
		return result;
	}
}
