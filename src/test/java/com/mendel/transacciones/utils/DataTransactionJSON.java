package com.mendel.transacciones.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class DataTransactionJSON {
	
	public static JSONObject getJSONObjectTransaction() throws JSONException {
		JSONObject transactionJsonObject = new JSONObject();
		transactionJsonObject.put("amount", 13);
		transactionJsonObject.put("parent_id", 0);
		transactionJsonObject.put("type", "someType");		
		return transactionJsonObject;
	}
	public static JSONObject getJSONObjectTransactionAutoreference() throws JSONException {
		JSONObject transactionJsonObject = new JSONObject();
		transactionJsonObject.put("amount", 17);
		transactionJsonObject.put("parent_id", 22);
		transactionJsonObject.put("type", "someType");		
		return transactionJsonObject;
	}
}
