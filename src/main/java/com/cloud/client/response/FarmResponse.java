package com.cloud.client.response;

import java.util.List;

import com.cloud.client.wsclient.domain.Farm;
 

public class FarmResponse {

	private List<Farm> farms;

	public List<Farm> getFarms() {
		return farms;
	}

	public void setFarms(List<Farm> farms) {
		this.farms = farms;
	}

}
