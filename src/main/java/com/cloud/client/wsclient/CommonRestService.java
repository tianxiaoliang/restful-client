package com.cloud.client.wsclient;

import java.util.Map;

import org.apache.log4j.Logger;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class CommonRestService {
	private Logger logger = Logger.getLogger(CommonRestService.class);

	private static final boolean DEBUG = false;

	protected String endpoint;
	protected String apiKey;
	protected String apiSecret;
	private OAuthService oAuthService;

	public CommonRestService(String endpoint, String apiKey, String apiSecret) {
		if (endpoint.endsWith("/"))
			endpoint = endpoint.substring(0, endpoint.length() - 1);
		this.endpoint = endpoint;
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
		if (DEBUG) {
			this.oAuthService = new ServiceBuilder()
					.provider(DummyAPIProvider.class).debug()
					.apiKey(this.apiKey).apiSecret(this.apiSecret).build();
		} else {
			this.oAuthService = new ServiceBuilder()
					.provider(DummyAPIProvider.class).apiKey(this.apiKey)
					.apiSecret(this.apiSecret).build();
		}
	}

	public String sendRequest(OAuthRequest request)   {
		request.addHeader("Content-Type", "application/json");

		Map<String, String> headers = request.getHeaders();

		logger.info("Request Headers:");
		logger.info("---------------------------------------"
				+ request.getCompleteUrl());
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			logger.info("    " + entry.getKey() + "=" + entry.getValue());
		}

		Token accessToken = new Token("", "");
		oAuthService.signRequest(accessToken, request);
		Response response = request.send();

		int code = response.getCode();
		logger.info("response code=" + code);
		// String strMsg = response.getMessage();
		String jsonResponse = response.getBody();
		logger.info("jsonResponse=" + jsonResponse);

		// print response json pretty
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(jsonResponse);
		String prettyJsonString = gson.toJson(je);
		if (DEBUG)
			logger.debug("\n" + prettyJsonString);
 

		return jsonResponse;
	}

	private static String formatJSON(String jsonStr) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(jsonStr);
		String prettyJsonString = gson.toJson(je);
		return prettyJsonString;
	}
}
