package com.example.demo.webservice.webservices;

import org.json.JSONObject;

public interface WSFunction {
	public WSFunctionEnum getWSFunction();
	public void addToMapParemeters();
	public JSONObject getParameters();
}
