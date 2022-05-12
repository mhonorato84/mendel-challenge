package com.mendel.transacciones.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class DataTransactionJSON {
	
	public static JSONObject getJSONObjectTransaction() throws JSONException {
		JSONObject transactionJsonObject = new JSONObject();
		transactionJsonObject.put("amount", 1);
		transactionJsonObject.put("parent_id", 0);
		transactionJsonObject.put("type", "someType");		
		return transactionJsonObject;
	}
}
