package com.cloud.client.wsclient;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;  
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.cloud.client.requests.FarmRegistryRequest;
import com.cloud.client.response.FarmResponse;
import com.cloud.client.wsclient.domain.Farm;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class FarmRestService extends CommonRestService { 
	public FarmRestService(String endpoint, String apiKey, String apiSecret) {
		super(endpoint, apiKey, apiSecret); 
	}

	private Logger logger = Logger.getLogger(FarmRestService.class);

	private static final boolean DEBUG = false;
 
	private OAuthService oAuthService;
	static Token accessToken = new Token(
			"rtcIe1nbtwhG8fjfSPnUiIM4hWegrWGNGeRokHdI", "");
	private static String URI = "/ws/farm";

	public List<Farm> getFarms(String endpoint) {

		OAuthRequest request = new OAuthRequest(Verb.GET, this.endpoint + URI);
		request.addQuerystringParameter("scalr_endpoint", endpoint);

		String jsonResponse = this.sendRequest(request);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FarmResponse response = gson.fromJson(jsonResponse, FarmResponse.class);
		return response.getFarms();
	}

	public String register(FarmRegistryRequest farm)  {

		OAuthRequest request = new OAuthRequest(Verb.POST, this.endpoint + URI
				+ "/register");
		request.addPayload(new Gson().toJson(farm));
		String jsonResponse = this.sendRequest(request);

		return jsonResponse;
	}
}
