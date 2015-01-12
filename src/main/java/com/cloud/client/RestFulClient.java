package com.cloud.client;

import org.cloud.client.util.Configuration;

import com.cloud.client.wsclient.FarmRestService;

/**
 * 
 * @author tian
 *
 */
public class RestFulClient {
	private String endpoint;
	private String apiKey;
	private String apiSecret;

	public RestFulClient() {
		Configuration.init();
		this.endpoint = Configuration.getValue("devops.endpoint"); 
		this.apiKey = Configuration.getValue("devops.apiKey");
		this.apiSecret = Configuration.getValue("devops.apiSecret");
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	public RestFulClient(String endpoint, String apiKey, String apiSecret) {
		this.endpoint = endpoint;
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
	}

	public FarmRestService getFarmService() { 
		return new FarmRestService(endpoint, apiKey, apiSecret);
	}

}
