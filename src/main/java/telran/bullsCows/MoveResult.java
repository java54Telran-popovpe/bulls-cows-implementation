package telran.bullsCows;

import org.json.JSONObject;

public record MoveResult( String clientSequence, int bullsN, int cowsN ) {
	public String getJSON() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("clientSeq", clientSequence);
		jsonObject.put("bullsN", bullsN);
		jsonObject.put("cowsN", cowsN);
		return jsonObject.toString();
	}
}
