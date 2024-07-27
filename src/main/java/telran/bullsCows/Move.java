package telran.bullsCows;

import org.json.JSONObject;

public record Move(long gameId, String clientSeq) {
	public String getJSON() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("gameID", Long.toUnsignedString(gameId));
		jsonObject.put("clientSeq", clientSeq);
		return jsonObject.toString();
	}
}
