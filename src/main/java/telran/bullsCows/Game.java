package telran.bullsCows;

import java.time.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {
	
	private static final long ANSWER_LENGTH = 4;
	private final long ID;
	private final String answer;
	private boolean isOver = false;
	private LocalDateTime start = LocalDateTime.now();
	private LocalDateTime finish = null;
	private List<MoveResult> results = new LinkedList<>();
	
	public Game(long ID) {
		this(ID, getRandomtAnswer());
	}
	
	public Game(long ID, String answer) {
		this.ID = ID;
		this.answer = answer; 
	}

	static private String getRandomtAnswer() {
		String res = new Random().ints(0, 10).distinct().limit(ANSWER_LENGTH).mapToObj( i-> String.valueOf(i)).collect(Collectors.joining());
		return res;
	}
	
	public boolean isOver() {
		return isOver;
	}

	public List<MoveResult> getGameFlow() {
		return results;
	}
	

	public List<MoveResult> moveProcess( Move move ) {
		if ( isOver ) {
			throw new IllegalStateException("Coudn't process move for finished game");
		}
		checkArgument(move);
		int bullsN = 0;
		int cowsN = 0;
		String clientSeq = move.clientSeq();
		for ( int i = 0; i < ANSWER_LENGTH; i++) {
			if ( clientSeq.charAt(i) == answer.charAt(i)  ) {
				bullsN++;
			} else {
				if ( answer.indexOf(clientSeq.charAt(i)) > -1 )  {
					cowsN++;
				}
			}
		}
		if ( bullsN == 4) {
			isOver = true;
			finish = LocalDateTime.now();
		}
		results.add(new MoveResult(clientSeq, bullsN, cowsN));
		return results;
	}

	private void checkArgument(Move move) {
		long moveGameID = move.gameId();
		if ( moveGameID != ID ) {
			throw new IllegalArgumentException(String.format("Game with ID %d couldn't process move with ID %d", ID, moveGameID));
		}
		String moveSeq = move.clientSeq();
		if ( !isSeqFollowTheRules(moveSeq)) {
			throw new IllegalArgumentException(String.format("Player sequence %s doesn't comply the rules", moveSeq));
		}
	}

	private boolean isSeqFollowTheRules(String clientSeq) {
		return clientSeq.length() == ANSWER_LENGTH && clientSeq.chars().boxed().collect(Collectors.toSet()).size() == ANSWER_LENGTH;
	}
	
	
	
}
