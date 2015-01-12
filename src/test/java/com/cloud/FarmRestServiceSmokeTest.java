package com.cloud;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger; 
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cloud.client.RestFulClient;
import com.cloud.client.requests.FarmRegistryRequest;
import com.cloud.client.wsclient.FarmRestService;
import com.cloud.client.wsclient.domain.Farm;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FarmRestServiceSmokeTest {

	private static Logger logger = Logger
			.getLogger(FarmRestServiceSmokeTest.class);

	private static final String ENDPOINT = "http://localhost:8080/";
	private static final String API_KEY = "devopsadmin";
	private static final String API_SECRET = "devops2014";

	private FarmRestService service;

	@Before
	public void init() {
		RestFulClient c = new RestFulClient();
		c.setApiKey("devopsadmin");
		c.setApiSecret("devops2014");
		c.setEndpoint("http://localhost:8080/");
		service = c.getFarmService();
	}

	@Test
	public void testGetByParams() throws Exception {

		logger.info("Get farm");
		List<Farm> farms = this.service.getFarms("cloudfarms");
		for (Farm c : farms) {
			logger.info("content===" + c.getScalrFarmName());
		}
	}

	@Test
	public void testCreateCollector() throws Exception {

		logger.info("testCreateCollector");
		Farm farm = new Farm();
		farm.setAccountId(9);
		farm.setScalrEndpoint("cloudfarms");
		farm.setScalrEnvId("71");
		farm.setScalrEnvName("bst-dev");
		farm.setScalrFarmId("1041");
		farm.setScalrFarmName("bst-devops-production1");
		farm.setSystemName("devops");
		farm.setSystemType("devops");
		farm.setFarmType("dev");

		FarmRegistryRequest r = new FarmRegistryRequest();
		r.setFarm(farm);
		service.register(r);
	}
}
