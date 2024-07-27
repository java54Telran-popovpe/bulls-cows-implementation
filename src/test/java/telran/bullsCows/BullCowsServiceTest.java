package telran.bullsCows;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public  class BullCowsServiceTest {
	BullsCowsMapImplTest games;
	long knownGameId;
	List<MoveResult> expectedResults;
	
	@BeforeEach
	void setUp() {
		games = new BullsCowsMapImplTest();
		knownGameId = games.createNewGame("1234");
		expectedResults = new LinkedList<>();
	}
	@Test
	void testGameFlow() {
		assertEquals(false, games.isGameOver(knownGameId));
		assertThrowsExactly(IllegalArgumentException.class, () -> games.getResults(knownGameId, new Move(knownGameId + 1, "1234")));
		assertThrowsExactly(IllegalArgumentException.class, () -> games.getResults(knownGameId + 1, new Move(knownGameId + 1, "1234")));
		assertThrowsExactly(IllegalArgumentException.class, () -> games.getResults(knownGameId + 1, new Move(knownGameId + 1, "1234")));
		assertThrowsExactly(IllegalArgumentException.class, () -> games.getResults(knownGameId + 1, new Move(knownGameId + 1, "123")));
		assertThrowsExactly(IllegalArgumentException.class, () -> games.getResults(knownGameId + 1, new Move(knownGameId + 1, "1233")));
		
		testNextMove("5678", 0, 0);
		testNextMove("5678", 0, 0);
		testNextMove("4321", 0, 4);
		testNextMove("1243", 2, 2);
		testNextMove("1238", 3, 0);
		testNextMove("1234", 4, 0);
		
		assertEquals(true, games.isGameOver(knownGameId));
		assertThrowsExactly(IllegalStateException.class, () -> games.getResults(knownGameId, new Move(knownGameId, "1233")));

		
	}
	
	private void testNextMove(String clientSeq, int expectedBulls, int expectedCows) {
		expectedResults.add(new MoveResult(clientSeq, expectedBulls, expectedCows));
		assertIterableEquals(expectedResults, games.getResults(knownGameId, new Move(knownGameId, clientSeq)));
	}
}
