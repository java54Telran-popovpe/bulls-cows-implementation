package telran.bullsCows;

import java.util.List;

public interface BullsCowsService {
	long createNewGame();
	List<MoveResult> getResults(long gameID, Move move );
	boolean isGameOver(long gameID);
}
